package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.MarketingEventDao;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.exception.WineCellarDataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Michaela Bamburová on 08.11.2016.
 */
@Service
public class MarketingEventServiceImpl implements MarketingEventService {

    @Inject
    private MarketingEventDao marketingEventDao;

    @Override
    public void createMarketingEvent(MarketingEvent marketingEvent) {
        try {
            marketingEventDao.createMarketingEvent(marketingEvent);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("Cannot create marketing event", e);
        }
    }

    @Override
    public void updateMarketingEvent(MarketingEvent marketingEvent) {
        try {
            marketingEventDao.updateMarketingEvent(marketingEvent);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("Cannot update marketing event", e);
        }
    }

    @Override
    public MarketingEvent findMarketingEventById(Long marketingEventId) {
         try {
             return marketingEventDao.findMarketingEventById(marketingEventId);
         } catch (Exception e) {
             throw new WineCellarDataAccessException("Cannot find marketing event by id", e);
         }
    }

    @Override
    public void deleteMarketingEvent(MarketingEvent marketingEvent) {
        try {
            marketingEventDao.deleteMarketingEvent(marketingEvent);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("Cannot delete marketing event", e);
        }
    }

    @Override
    public List<MarketingEvent> findAllMarketingEvents() {
        try {
            return marketingEventDao.findAllMarketingEvents();
        } catch (Exception e) {
            throw new WineCellarDataAccessException("Cannot find all marketing events", e);
        }
    }
}
