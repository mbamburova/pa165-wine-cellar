package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.marketingEvent.MarketingEventDto;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.MarketingEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */

@Service
@Transactional
public class MarketingEventFacadeImpl implements MarketingEventFacade {

    @Inject
    private BeanMappingService beanMappingService;

    @Inject
    private MarketingEventService marketingEventService;

    @Override
    public Long createMarketingEvent(MarketingEventDto marketingEventDto) {
        if (marketingEventDto == null) {
            throw new IllegalArgumentException("marketingEvent is null!");
        }
        MarketingEvent mappedMarketingEvent = beanMappingService.mapTo(marketingEventDto, MarketingEvent.class);
        marketingEventService.createMarketingEvent(mappedMarketingEvent);
        return mappedMarketingEvent.getId();
    }

    @Override
    public void deleteMarketingEvent(Long marketingEventId) {
        MarketingEvent event = marketingEventService.findMarketingEventById(marketingEventId);
        marketingEventService.deleteMarketingEvent(event);
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
