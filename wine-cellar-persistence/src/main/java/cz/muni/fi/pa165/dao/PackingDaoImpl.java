package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Packing;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
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
        em.remove(packing);
    }

    @Override
    public void updatePacking(Packing packing) {
        em.merge(packing);
    }

    @Override
    public Packing findById(Long id) {
        return em.find(Packing.class, id);
    }

    @Override
    public List<Packing> findPackingsByVolume(BigDecimal volume) {
        if (volume == null)
            throw new IllegalArgumentException("Cannot search for null volume");
        try {
            return em
                .createQuery("SELECT p FROM Packing p WHERE p.volume = :volume",
                    Packing.class).setParameter("volume", volume)
                .getResultList();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Packing> findAll() {
        return Collections.unmodifiableList(em.createQuery("SELECT p FROM Packing p", Packing.class).getResultList());
    }
}
