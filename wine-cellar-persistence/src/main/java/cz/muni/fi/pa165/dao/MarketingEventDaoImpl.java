package cz.muni.fi.pa165.dao;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author MarekScholtz
 * @version 2016.10.29
 */
@Repository
public class MarketingEventDaoImpl implements MarketingEventDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(MarketingEvent marketingEvent) {
        entityManager.persist(marketingEvent);
    }

    @Override
    public MarketingEvent get(Long id) {
        return entityManager.find(MarketingEvent.class, id);
    }

    @Override
    public void update(MarketingEvent marketingEvent) {
        entityManager.merge(marketingEvent);
    }

    @Override
    public void delete(MarketingEvent marketingEvent) {
        entityManager.remove(marketingEvent);
    }

    @Override
    public List<MarketingEvent> getAll() {
        return entityManager.createQuery("SELECT me FROM MarketingEvent me", MarketingEvent.class).getResultList();
    }
}
