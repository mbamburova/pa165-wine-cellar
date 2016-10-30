package cz.muni.fi.pa165.dao;

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
