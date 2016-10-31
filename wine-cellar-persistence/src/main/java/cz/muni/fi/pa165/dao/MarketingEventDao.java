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
     * @param marketingEvent
     */
    void create(MarketingEvent marketingEvent);

    /**
     * get marketingEvent by id from database
     * @param id
     * @return
     */
    MarketingEvent get(Long id);

    /**
     * update marketingEvent in database
     * @param marketingEvent
     */
    void update(MarketingEvent marketingEvent);

    /**
     * delete marketingEvent from database
     * @param marketingEvent
     */
    void delete(MarketingEvent marketingEvent);

    /**
     * get all marketingEvents from database
     * @return list of marketingEvents
     */
    List<MarketingEvent> getAll();
}
