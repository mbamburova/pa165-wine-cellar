package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public interface MarketingEventFacade {

    void create(MarketingEventDto marketingEvent);
    void delete(Long marketingEventId);
    void update(Long marketingEventId);
    void updateDescription(Long marketingEventId, String name);


    List<MarketingEventDto> findAll();
    MarketingEventDto get(Long id);
    List<MarketingEventDto> findByDescription(String name);


    MarketingEventDto addPrice(Long marketingEventId, Long priceId);
    MarketingEventDto removePrice(Long marketingEventId, Long priceId);
}
