package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.WineListDto;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.WineList;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.MarketingEventService;
import cz.muni.fi.pa165.service.WineListService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public class WineListFacadeImpl implements WineListFacade {

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private WineListService wineListService;

    @Autowired
    private MarketingEventService marketingEventService;

    @Override
    public void createWineList(WineListDto wineListDto) {
        if (wineListDto == null) {
            throw new IllegalArgumentException("PriceCreateDto is null!");
        }
        WineList mappedWineList = beanMappingService.mapTo(wineListDto, WineList.class);
        mappedWineList.setMarketingEvent(marketingEventService.findMarketingEventById(wineListDto.getMarketingEvent().getId()));
        wineListService.createWineList(mappedWineList);
    }

    @Override
    public void deleteWineList(WineListDto wineListDto) {
        WineList mappedWineList = beanMappingService.mapTo(wineListDto, WineList.class);
        wineListService.deleteWineList(mappedWineList);
    }

    @Override
    public void updateWineList(WineListDto wineListDto) {
        WineList mappedWineList = beanMappingService.mapTo(wineListDto, WineList.class);
        wineListService.updateWineList(mappedWineList);
    }

    @Override
    public List<WineListDto> findAllWineLists() {
        return beanMappingService.mapToCollection(wineListService.findAllWineLists(), WineListDto.class);
    }

    @Override
    public WineListDto findWineListById(Long id) {
        if (wineListService.findWineListById(id) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapTo(wineListService.findWineListById(id), WineListDto.class);
    }

    @Override
    public List<WineListDto> findWineListsByName(String name) {
        if (wineListService.findWineListByName(name) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(wineListService.findWineListByName(name), WineListDto.class);
    }

    @Override
    public List<WineListDto> findWineListsByDate(LocalDateTime date) {
        if (wineListService.findWineListByDate(date) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(wineListService.findWineListByDate(date), WineListDto.class);
    }

    @Override
    public List<WineListDto> findWineListsByMarketingEvent(MarketingEventDto marketingEventDto) {
        MarketingEvent mappedMarketingEvent = beanMappingService.mapTo(marketingEventDto, MarketingEvent.class);

        if (wineListService.findWineListByMarketingEvent(mappedMarketingEvent) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection((Collection<?>) wineListService.findWineListByMarketingEvent(mappedMarketingEvent), WineListDto.class);
    }
}
