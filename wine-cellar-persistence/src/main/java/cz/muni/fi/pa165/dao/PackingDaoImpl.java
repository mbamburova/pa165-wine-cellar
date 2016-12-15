package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Wine;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Michaela Bamburová on 25.10.2016.
 */
@Repository
public class PackingDaoImpl implements PackingDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createPacking(Packing packing) {
        em.persist(packing);
    }

    @Override
    public void deletePacking(Packing packing) {
        em.remove(findPackingById(packing.getId()));
    }

    @Override
    public void updatePacking(Packing packing) {
        em.merge(packing);
    }

    @Override
    public Packing findPackingById(Long id) {
        return em.find(Packing.class, id);
    }

    @Override
    public List<Packing> findPackingsByVolume(BigDecimal volume) {
        if (volume == null)
            throw new IllegalArgumentException("Cannot search for null volume");

        return em
                .createQuery("SELECT p FROM Packing p WHERE p.volume = :volume",
                    Packing.class).setParameter("volume", volume)
                .getResultList();
    }

    @Override
    public List<Packing> findAllPackings() {
        return em.createQuery("SELECT p FROM Packing p", Packing.class).getResultList();
    }

    @Override
    public List<Packing> findPackingsByWine(Wine wine) {
        if (wine == null)
            throw new IllegalArgumentException("Cannot search for null wine");

        return em.createQuery("select p from Packing p where p.wine = :wine",
                Packing.class).setParameter("wine", wine).getResultList();
    }
}
