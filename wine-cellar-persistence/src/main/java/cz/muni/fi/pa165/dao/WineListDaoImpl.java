package cz.muni.fi.pa165.dao;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;

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
}
