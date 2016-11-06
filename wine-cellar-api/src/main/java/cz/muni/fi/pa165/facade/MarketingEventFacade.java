package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;

import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public interface MarketingEventFacade {

    List<MarketingEventDto> getAllMarketingEvents();
    MarketingEventDto getMarketingEventById();
    List<MarketingEventDto> getMarketingEventByDescription();
}
