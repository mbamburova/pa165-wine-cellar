package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.marketingEvent.MarketingEventDto;
import cz.muni.fi.pa165.dto.wine.WineDto;
import cz.muni.fi.pa165.dto.wineList.WineListCreateDto;
import cz.muni.fi.pa165.dto.wineList.WineListDto;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.entity.WineList;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.MarketingEventService;
import cz.muni.fi.pa165.service.WineListService;
import cz.muni.fi.pa165.service.WineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
@Service
@Transactional
public class WineListFacadeImpl implements WineListFacade {

    @Inject
    private BeanMappingService beanMappingService;

    @Inject
    private WineListService wineListService;

    @Inject
    private WineService wineService;

    @Inject
    private MarketingEventService marketingEventService;

    @Override
    public Long createWineList(WineListCreateDto wineListDto) {
        if (wineListDto == null) {
            throw new IllegalArgumentException("WineListDTO cannot be null");
        }
        WineList wineList = beanMappingService.mapTo(wineListDto, WineList.class);
        if (wineListDto.getMarketingEventId() != null) {
            wineList.setMarketingEvent(marketingEventService.findMarketingEventById(wineListDto.getMarketingEventId()));
        }
        List<Long> wines = wineListDto.getWinesIds();
        for (Long wineId : wines) {
            wineList.addWine(wineService.findWineById(wineId));
        }
        wineListService.createWineList(wineList);
        return wineList.getId();
    }

    @Override
    public void deleteWineList(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Winelist ID cannot be null");
        }
        WineList wineList = wineListService.findWineListById(id);
        wineListService.deleteWineList(wineList);
    }

    @Override
    public void updateWineList(WineListDto wineListDto) {
        if (wineListDto == null) {
            throw new IllegalArgumentException("WineListDTO cannot be null");
        }
        WineList wineList = beanMappingService.mapTo(wineListDto, WineList.class);
        if (wineListDto.getMarketingEvent().getId() != null) {
            wineList.setMarketingEvent(marketingEventService.findMarketingEventById(wineListDto.getMarketingEvent().getId()));
        } else wineList.setMarketingEvent(null);

        wineListService.updateWineList(wineList);
    }

    @Override
    public List<WineListDto> findAllWineLists() {
        return beanMappingService.mapToCollection(wineListService.findAllWineLists(), WineListDto.class);
    }

    @Override
    public WineListDto findWineListById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Winelist ID cannot be null");
        }
        WineList wineList = wineListService.findWineListById(id);
        return beanMappingService.mapTo(wineList, WineListDto.class);
    }

    @Override
    public List<WineListDto> findWineListsByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Winelist name cannot be null");
        }
        return beanMappingService.mapToCollection(wineListService.findWineListByName(name), WineListDto.class);
    }

    @Override
    public List<WineListDto> findWineListsByDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("Winelist date cannot be null");
        }
        return beanMappingService.mapToCollection(wineListService.findWineListByDate(date), WineListDto.class);
    }

    @Override
    public List<WineListDto> findWineListsByMarketingEvent(MarketingEventDto marketingEventDto) {
        if (marketingEventDto == null) {
            throw new IllegalArgumentException("marketingEventDto cannot be null");
        }
        MarketingEvent marketingEvent = beanMappingService.mapTo(marketingEventDto, MarketingEvent.class);
        return beanMappingService.mapToCollection(wineListService.findWineListByMarketingEvent(marketingEvent), WineListDto.class);
    }

    @Override
    public void addWine(WineListDto wineListDto, WineDto wineDto) {
        if (wineListDto == null) {
            throw new IllegalArgumentException("Winelist cannot be null");
        }
        if (wineDto == null) {
            throw new IllegalArgumentException("Wine cannot be null");
        }
        if (wineListDto.getWines().contains(wineDto)) {
            throw new UnsupportedOperationException("Winelist already contains wine with id " + wineDto.getId());
        }
        WineList wineList = beanMappingService.mapTo(wineListDto, WineList.class);
        if (wineListDto.getMarketingEvent().getId() != null) {
            wineList.setMarketingEvent(marketingEventService.findMarketingEventById(wineListDto.getMarketingEvent().getId()));
        } else wineList.setMarketingEvent(null);

        wineListService.addWine(wineList, beanMappingService.mapTo(wineDto, Wine.class));
    }

    @Override
    public void removeWine(WineListDto wineListDto, WineDto wineDto) {
        if (wineListDto == null) {
            throw new IllegalArgumentException("Winelist cannot be null");
        }
        if (wineDto == null) {
            throw new IllegalArgumentException("Wine cannot be null");
        }
        WineList wineList = beanMappingService.mapTo(wineListDto, WineList.class);
        if (wineListDto.getMarketingEvent().getId() != null) {
            wineList.setMarketingEvent(marketingEventService.findMarketingEventById(wineListDto.getMarketingEvent().getId()));
        } else wineList.setMarketingEvent(null);

        wineListService.removeWine(wineList, beanMappingService.mapTo(wineDto, Wine.class));
    }

}
