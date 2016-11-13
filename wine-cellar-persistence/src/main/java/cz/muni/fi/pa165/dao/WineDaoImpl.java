package cz.muni.fi.pa165.dao;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import cz.muni.fi.pa165.entity.Wine;
import org.springframework.stereotype.Repository;

/**
 * @author Silvia Borzová
 *         31/10/2016
 */

@Repository
public class WineDaoImpl implements WineDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createWine(Wine wine) {
        em.persist(wine);
    }

    @Override
    public void updateWine(Wine wine) {
        em.merge(wine);
    }

    @Override
    public void deleteWine(Wine wine) {
        em.remove(wine);
    }

    @Override
    public Wine findWineById(Long id) {
        return em.find(Wine.class, id);
    }

    @Override
    public List<Wine> findAllWines() {
        return em.createQuery("SELECT w FROM Wine w", Wine.class)
                .getResultList();
    }

    @Override
    public List<Wine> findByName(String name) {
        try {
            return em
                    .createQuery("SELECT w FROM Wine w WHERE w.name = :name",
                            Wine.class).setParameter("name", name)
                    .getResultList();
        } catch (NoResultException nrf) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Wine> findByPredicate(String predicate) {
        try {
            return em
                    .createQuery("SELECT w FROM Wine w WHERE w.predicate = :predicate",
                            Wine.class).setParameter("predicate", predicate)
                    .getResultList();
        } catch (NoResultException nrf) {
            return new ArrayList<>();
        }
    }
}
