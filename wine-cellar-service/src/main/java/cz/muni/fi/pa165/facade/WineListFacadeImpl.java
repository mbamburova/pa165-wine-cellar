package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.dto.WineListCreateDto;
import cz.muni.fi.pa165.dto.WineListDto;
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
    private WineService wineService;

    @Inject
    private MarketingEventService marketingEventService;

    @Override
    public Long createWineList(WineListCreateDto wineListDto) {
        if (wineListDto == null) {
            throw new IllegalArgumentException("WineListDTO cannot be null");
        }

        WineList wineList = beanMappingService.mapTo(wineListDto, WineList.class);
        wineList.setMarketingEvent(marketingEventService.findMarketingEventById(wineListDto.getMarketingEventId()));

        List<Long> winesIds = wineListDto.getWinesIds();
        for (Long id : winesIds){
            wineList.addWine(wineService.findWineById(id));
        }

        wineListService.createWineList(wineList);
        return wineList.getId();
    }

    @Override
    public void deleteWineList(Long id) {
        if (id == null){
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
        wineList.setMarketingEvent(marketingEventService.findMarketingEventById(wineListDto.getMarketingEvent().getId()));

        List<WineDto> wines = wineListDto.getWines();
        for (WineDto wineDto : wines){
            wineList.addWine(wineService.findWineById(wineDto.getId()));
        }
        wineListService.updateWineList(wineList);
    }

    @Override
    public List<WineListDto> findAllWineLists() {
        List<WineListDto> wineList = beanMappingService.mapToCollection(wineListService.findAllWineLists(), WineListDto.class);
        return createDtos(wineList);
    }

    @Override
    public WineListDto findWineListById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Winelist ID cannot be null");
        }

        WineList wineList = wineListService.findWineListById(id);
        WineListDto wineListDto = beanMappingService.mapTo(wineList, WineListDto.class);
        wineListDto.setMarketingEvent(beanMappingService.mapTo(
                marketingEventService.findMarketingEventById(wineList.getMarketingEvent().getId()), MarketingEventDto.class));

        List<Wine> wines = wineList.getWines();
        for (Wine wine : wines){
            wineListDto.addWine(beanMappingService.mapTo(wineService.findWineById(wine.getId()), WineDto.class));
        }

        return wineListDto;
    }

    @Override
    public List<WineListDto> findWineListsByName(String name) {
        if (name == null){
            throw new IllegalArgumentException("Winelist name cannot be null");
        }
        List<WineListDto> wineList = beanMappingService.mapToCollection(wineListService.findWineListByName(name), WineListDto.class);
        return createDtos(wineList);
    }

    @Override
    public List<WineListDto> findWineListsByDate(LocalDateTime date) {
        if (date == null){
            throw new IllegalArgumentException("Winelist date cannot be null");
        }
        List<WineListDto> wineList = beanMappingService.mapToCollection(wineListService.findWineListByDate(date), WineListDto.class);
        return createDtos(wineList);
    }

    @Override
    public List<WineListDto> findWineListsByMarketingEvent(MarketingEventDto marketingEventDto) {
        if (marketingEventDto == null){
            throw new IllegalArgumentException("marketingEventDto cannot be null");
        }
        MarketingEvent marketingEvent = beanMappingService.mapTo(marketingEventDto, MarketingEvent.class);
        List<WineListDto> wineList = beanMappingService.mapToCollection(
                wineListService.findWineListByMarketingEvent(marketingEvent), WineListDto.class);
        return createDtos(wineList);
    }

    private List<WineListDto> createDtos(List<WineListDto> wineList){
        for (WineListDto wineListDto : wineList) {
            wineListDto.setMarketingEvent(beanMappingService.mapTo(
                    marketingEventService.findMarketingEventById(wineListDto.getMarketingEvent().getId()), MarketingEventDto.class
            ));
            for (WineDto wineDto : wineListDto.getWines()){
                wineListDto.addWine(beanMappingService.mapTo(
                        wineService.findWineById(wineDto.getId()), WineDto.class
                ));
            }
            wineListDto.setWines(beanMappingService.mapToCollection(wineListDto.getWines(), WineDto.class));
        }
        return wineList;
    }
}
