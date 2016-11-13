package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.MarketingEvent;

import java.util.List;

/**
 * @author Michaela Bamburová on 08.11.2016.
 */
public interface MarketingEventService {

    void createMarketingEvent(MarketingEvent marketingEvent);
    void updateMarketingEvent(MarketingEvent marketingEvent);
    MarketingEvent findMarketingEventById(Long marketingEventId);
    void deleteMarketingEvent(MarketingEvent marketingEvent);
    List<MarketingEvent> findAllMarketingEvents();
}
