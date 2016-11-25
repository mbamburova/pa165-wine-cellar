package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.MarketingEventService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public class MarketingEventFacadeImpl implements MarketingEventFacade {

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private MarketingEventService marketingEventService;

    @Override
    public void createMarketingEvent(MarketingEventDto marketingEventDto) {

        if (marketingEventDto == null) {
            throw new IllegalArgumentException("marketingEvent is null!");
        }
        MarketingEvent mappedMarketingEvent = beanMappingService.mapTo(marketingEventDto, MarketingEvent.class);
        marketingEventService.createMarketingEvent(mappedMarketingEvent);
    }

    @Override
    public void deleteMarketingEvent(MarketingEventDto marketingEventDto) {
        MarketingEvent mappedMarketingEvent = beanMappingService.mapTo(marketingEventDto, MarketingEvent.class);
        marketingEventService.deleteMarketingEvent(mappedMarketingEvent);
    }

    @Override
    public void updateMarketingEvent(MarketingEventDto marketingEventDto) {
        MarketingEvent mappedMarketingEvent = beanMappingService.mapTo(marketingEventDto, MarketingEvent.class);
        marketingEventService.updateMarketingEvent(mappedMarketingEvent);
    }

    @Override
    public List<MarketingEventDto> findAllMarketingEvents() {
        return beanMappingService.mapToCollection(marketingEventService.findAllMarketingEvents(), MarketingEventDto.class);
    }

    @Override
    public MarketingEventDto findMarketingEventById(Long id) {
        if (marketingEventService.findMarketingEventById(id) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapTo(marketingEventService.findMarketingEventById(id), MarketingEventDto.class);
    }
}
