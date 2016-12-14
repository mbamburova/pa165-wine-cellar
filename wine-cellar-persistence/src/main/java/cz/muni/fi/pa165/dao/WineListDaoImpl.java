package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.WineList;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public void createWineList(WineList wineList) {
        entityManager.persist(wineList);
    }

    @Override
    public WineList findWineListById(Long id) {
        return entityManager.find(WineList.class, id);
    }

    @Override
    public void updateWineList(WineList wineList) {
        entityManager.merge(wineList);
    }

    @Override
    public void deleteWineList(WineList wineList) {
        entityManager.remove(findWineListById(wineList.getId()));
    }

    @Override
    public List<WineList> findAllWineLists() {
        return Collections.unmodifiableList(
                entityManager.createQuery("SELECT wl FROM WineList wl", WineList.class).getResultList());
    }

    @Override
    public List<WineList> findWineListsByDate(LocalDateTime date) {
        return Collections.unmodifiableList(
            entityManager.createQuery("SELECT wl FROM WineList wl WHERE date = :date", WineList.class).setParameter("date", date).getResultList());

    }

    @Override
    public List<WineList> findWineListsByName(String name) {
        try {
            return entityManager
                    .createQuery("SELECT wl FROM WineList wl WHERE wl.name = :name",
                            WineList.class).setParameter("name", name)
                    .getResultList();
        } catch (NoResultException nrf) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<WineList> findWineListByMarketingEvent(MarketingEvent marketingEvent) {
        try {
            return entityManager
                    .createQuery("SELECT wl FROM WineList wl WHERE MARKETING_EVENT_ID = :marketingEventId", WineList.class).setParameter("marketingEventId", marketingEvent.getId())
                    .getResultList();
        } catch (NoResultException nrf) {
            return new ArrayList<>();
        }
    }
}