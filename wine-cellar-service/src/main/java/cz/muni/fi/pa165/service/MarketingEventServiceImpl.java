package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.MarketingEventDao;
import cz.muni.fi.pa165.entity.MarketingEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Michaela Bamburová on 08.11.2016.
 */
public class MarketingEventServiceImpl implements MarketingEventService {

    @Autowired
    private MarketingEventDao marketingEventDao;

    @Override
    public void createMarketingEvent(MarketingEvent marketingEvent) {
        marketingEventDao.create(marketingEvent);
    }

    @Override
    public void updateMarketingEvent(MarketingEvent marketingEvent) {
        marketingEventDao.update(marketingEvent);
    }

    @Override
    public MarketingEvent findMarketingEventById(Long marketingEventId) {
        return marketingEventDao.get(marketingEventId);
    }

    @Override
    public void deleteMarketingEvent(MarketingEvent marketingEvent) {
        marketingEventDao.delete(marketingEvent);
    }

    @Override
    public List<MarketingEvent> findAllMarketingEvents() {
        return null;
    }
}
