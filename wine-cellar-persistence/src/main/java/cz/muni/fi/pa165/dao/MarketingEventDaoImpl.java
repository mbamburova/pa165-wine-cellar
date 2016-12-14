package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.MarketingEvent;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.10.29
 */
@Repository
public class MarketingEventDaoImpl implements MarketingEventDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createMarketingEvent(MarketingEvent marketingEvent) {
        entityManager.persist(marketingEvent);
    }

    @Override
    public MarketingEvent findMarketingEventById(Long id) {
        return entityManager.find(MarketingEvent.class, id);
    }

    @Override
    public void updateMarketingEvent(MarketingEvent marketingEvent) {
        entityManager.merge(marketingEvent);
    }

    @Override
    public void deleteMarketingEvent(MarketingEvent marketingEvent) {
        entityManager.remove(findMarketingEventById(marketingEvent.getId()));
    }

    @Override
    public List<MarketingEvent> findAllMarketingEvents() {
        return Collections.unmodifiableList(
                entityManager.createQuery("SELECT me FROM MarketingEvent me", MarketingEvent.class).getResultList());
    }
}
