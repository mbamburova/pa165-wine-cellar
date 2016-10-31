package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.MarketingEvent;

import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.10.29
 */
public interface MarketingEventDao {

    void create(MarketingEvent marketingEvent);
    MarketingEvent get(Long id);
    void update(MarketingEvent marketingEvent);
    void delete(MarketingEvent marketingEvent);
    List<MarketingEvent> getAll();
}
