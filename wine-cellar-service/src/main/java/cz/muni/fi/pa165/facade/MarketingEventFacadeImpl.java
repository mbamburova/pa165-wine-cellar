package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;

import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public class MarketingEventFacadeImpl implements MarketingEventFacade {
    @Override
    public void create(MarketingEventDto marketingEvent) {

    }

    @Override
    public void delete(Long marketingEventId) {

    }

    @Override
    public void update(Long marketingEventId) {

    }

    @Override
    public void updateDescription(Long marketingEventId, String name) {

    }

    @Override
    public List<MarketingEventDto> findAll() {
        return null;
    }

    @Override
    public MarketingEventDto get(Long id) {
        return null;
    }

    @Override
    public List<MarketingEventDto> findByDescription(String name) {
        return null;
    }

    @Override
    public MarketingEventDto addPrice(Long marketingEventId, Long priceId) {
        return null;
    }

    @Override
    public MarketingEventDto removePrice(Long marketingEventId, Long priceId) {
        return null;
    }
}
