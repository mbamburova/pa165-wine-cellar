package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.marketingEvent.MarketingEventDto;

import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public interface MarketingEventFacade {

    Long createMarketingEvent(MarketingEventDto marketingEvent);

    void deleteMarketingEvent(Long id);

    void updateMarketingEvent(MarketingEventDto marketingEvent);

    List<MarketingEventDto> findAllMarketingEvents();

    MarketingEventDto findMarketingEventById(Long id);
}
