package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.WineList;
import org.joda.time.LocalDateTime;
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
public class WineListDaoImpl implements WineListDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(WineList wineList) {
        entityManager.persist(wineList);
    }

    @Override
    public WineList get(Long id) {
        return entityManager.find(WineList.class, id);
    }

    @Override
    public void update(WineList wineList) {
        entityManager.merge(wineList);
    }

    @Override
    public void delete(WineList wineList) {
        entityManager.remove(wineList);
    }

    @Override
    public List<WineList> getAll() {
        return Collections.unmodifiableList(
                entityManager.createQuery("SELECT wl FROM WineList wl", WineList.class).getResultList());
    }

    @Override
    public List<WineList> findByDate(LocalDateTime date) {
        return Collections.unmodifiableList(
            entityManager.createQuery("SELECT wl FROM WineList wl WHERE date = :date", WineList.class).setParameter("date", date).getResultList());

    }

    @Override
    public List<WineList> findByName(String name) {
        return Collections.unmodifiableList(
            entityManager.createQuery("SELECT wl FROM WineList wl WHERE name = :name", WineList.class).setParameter("name", name).getResultList());

    }

    @Override
    public WineList findByMarketingEvent(MarketingEvent marketingEvent) {
        return entityManager.createQuery("SELECT wl FROM WineList wl WHERE marketingEvent = :marketingEvent", WineList.class).setParameter("marketingEvent", marketingEvent).getSingleResult();
    }
}