package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public interface MarketingEventFacade {

    void createMarketingEvent(MarketingEventDto marketingEvent);
    void deleteMarketingEvent(MarketingEventDto marketingEvent);
    void updateMarketingEvent(Long marketingEventId);

    List<MarketingEventDto> findAllMarketingEvents();
    MarketingEventDto findMarketingEventById(Long id);
}
