package cz.muni.fi.pa165.dao;

import java.util.List;
import javax.persistence.EntityManager;
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
    public Wine getWineById(Long id) {
        return em.find(Wine.class, id);
    }

    @Override
    public List<Wine> getAllWines() {
        return em.createQuery("select w from Wine w", Wine.class)
                .getResultList();
    }
}
