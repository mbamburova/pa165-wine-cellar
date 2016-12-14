package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.dto.WineListDto;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.entity.WineList;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.MarketingEventService;
import cz.muni.fi.pa165.service.WineListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private MarketingEventService marketingEventService;

    @Override
    public Long createWineList(WineListDto wineListDto) {
        if (wineListDto == null) {
            throw new IllegalArgumentException("WineListDto is null!");
        }
        WineList wineList = new WineList();
        wineList.setId(wineListDto.getId());
        wineList.setName(wineListDto.getName());
        wineList.setDate(wineListDto.getDate());
        wineList.setWines(beanMappingService.mapToCollection(wineListDto.getWines(), Wine.class));
        if (wineListDto.getMarketingEvent() != null) {
            wineList.setMarketingEvent(beanMappingService.mapTo(wineListDto.getMarketingEvent(), MarketingEvent.class));
        }
        wineListService.createWineList(wineList);
        return wineList.getId();
    }

    @Override
    public void deleteWineList(Long wineListId) {
        WineList wineList = wineListService.findWineListById(wineListId);
        wineListService.deleteWineList(wineList);
    }

    @Override
    public void updateWineList(WineListDto wineListDto) {
        WineList wineList = new WineList();
        wineList.setId(wineListDto.getId());
        wineList.setName(wineListDto.getName());
        wineList.setDate(wineListDto.getDate());
        wineList.setWines(beanMappingService.mapToCollection(wineListDto.getWines(), Wine.class));
        if (wineListDto.getMarketingEvent() != null) {
            wineList.setMarketingEvent(beanMappingService.mapTo(wineListDto.getMarketingEvent(), MarketingEvent.class));
        }
        wineListService.updateWineList(wineList);
    }

    @Override
    public List<WineListDto> findAllWineLists() {
        List<WineListDto> wineLists = new ArrayList<>();
        for (WineList wineList : wineListService.findAllWineLists()) {
            WineListDto wineListDto = new WineListDto();
            wineListDto.setId(wineList.getId());
            wineListDto.setName(wineList.getName());
            wineListDto.setDate(wineList.getDate());
            wineListDto.setWines(beanMappingService.mapToCollection(wineList.getWines(), WineDto.class));
            if (wineList.getMarketingEvent() != null) {
                wineListDto.setMarketingEvent(beanMappingService.mapTo(wineList.getMarketingEvent(), MarketingEventDto.class));
            }
            wineLists.add(wineListDto);
        }
        return wineLists;
    }

    @Override
    public WineListDto findWineListById(Long id) {
        WineList wineList = wineListService.findWineListById(id);
        WineListDto wineListDto = new WineListDto();
        wineListDto.setId(wineList.getId());
        wineListDto.setName(wineList.getName());
        wineListDto.setDate(wineList.getDate());
        wineListDto.setWines(beanMappingService.mapToCollection(wineList.getWines(), WineDto.class));
        if (wineList.getMarketingEvent() != null) {
            wineListDto.setMarketingEvent(beanMappingService.mapTo(wineList.getMarketingEvent(), MarketingEventDto.class));
        }
        return wineListDto;
    }

    @Override
    public List<WineListDto> findWineListsByName(String name) {
        List<WineListDto> wineLists = new ArrayList<>();
        for (WineList wineList : wineListService.findWineListByName(name)) {
            WineListDto wineListDto = new WineListDto();
            wineListDto.setId(wineList.getId());
            wineListDto.setName(wineList.getName());
            wineListDto.setDate(wineList.getDate());
            wineListDto.setWines(beanMappingService.mapToCollection(wineList.getWines(), WineDto.class));
            if (wineList.getMarketingEvent() != null) {
                wineListDto.setMarketingEvent(beanMappingService.mapTo(wineList.getMarketingEvent(), MarketingEventDto.class));
            }
            wineLists.add(wineListDto);
        }
        return wineLists;
    }

    @Override
    public List<WineListDto> findWineListsByDate(LocalDateTime date) {
        List<WineListDto> wineLists = new ArrayList<>();
        for (WineList wineList : wineListService.findWineListByDate(date)) {
            WineListDto wineListDto = new WineListDto();
            wineListDto.setId(wineList.getId());
            wineListDto.setName(wineList.getName());
            wineListDto.setDate(wineList.getDate());
            wineListDto.setWines(beanMappingService.mapToCollection(wineList.getWines(), WineDto.class));
            if (wineList.getMarketingEvent() != null) {
                wineListDto.setMarketingEvent(beanMappingService.mapTo(wineList.getMarketingEvent(), MarketingEventDto.class));
            }
            wineLists.add(wineListDto);
        }
        return wineLists;
    }

    @Override
    public List<WineListDto> findWineListsByMarketingEvent(MarketingEventDto marketingEventDto) {
        List<WineListDto> wineLists = new ArrayList<>();
        for (WineList wineList : wineListService.findWineListByMarketingEvent(beanMappingService.mapTo(marketingEventDto, MarketingEvent.class))) {
            WineListDto wineListDto = new WineListDto();
            wineListDto.setId(wineList.getId());
            wineListDto.setName(wineList.getName());
            wineListDto.setDate(wineList.getDate());
            wineListDto.setWines(beanMappingService.mapToCollection(wineList.getWines(), WineDto.class));
            if (wineList.getMarketingEvent() != null) {
                wineListDto.setMarketingEvent(beanMappingService.mapTo(wineList.getMarketingEvent(), MarketingEventDto.class));
            }
            wineLists.add(wineListDto);
        }
        return wineLists;
    }
}
