package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Packing;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Michaela Bamburová on 25.10.2016.
 */
@Repository
public class PackingDAOImpl implements PackingDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createPacking(Packing p) {
        em.persist(p);
    }

    @Override
    public void deletePacking(Packing p) {
        em.remove(p);
    }

    @Override
    public Packing findById(Long id) {
        return em.find(Packing.class, id);
    }

    @Override
    public Packing findByVolume(BigDecimal volume) {
        try {
            return em
                .createQuery("select p from Packing p where volume = :volume",
                    Packing.class).setParameter(":volume", volume)
                .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Packing> findAll() {
        return em.createQuery("select p from Packing p", Packing.class).getResultList();
    }
}
