package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.MarketingEvent;

import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.10.29
 */
public interface MarketingEventDao {

    /**
     * create marketingEvent in database
     *
     * @param marketingEvent to create
     */
    void createMarketingEvent(MarketingEvent marketingEvent);

    /**
     * get marketingEvent by id from database
     *
     * @param id
     * @return
     */
    MarketingEvent findMarketingEventById(Long id);

    /**
     * update marketingEvent in database
     *
     * @param marketingEvent to update
     */
    void updateMarketingEvent(MarketingEvent marketingEvent);

    /**
     * delete marketingEvent from database
     *
     * @param marketingEvent to delete
     */
    void deleteMarketingEvent(MarketingEvent marketingEvent);

    /**
     * get all marketingEvents from database
     *
     * @return list of marketingEvents
     */
    List<MarketingEvent> findAllMarketingEvents();
}
