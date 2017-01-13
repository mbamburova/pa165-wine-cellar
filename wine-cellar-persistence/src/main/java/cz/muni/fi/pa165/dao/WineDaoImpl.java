package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Wine;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

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
        em.remove(findWineById(wine.getId()));
    }

    @Override
    public Wine findWineById(Long id) {
        return em.find(Wine.class, id);
    }

    @Override
    public List<Wine> findAllWines() {
        return em.createQuery("SELECT w FROM Wine w", Wine.class).getResultList();
    }

    @Override
    public List<Wine> findWinesByName(String name) {
        return em.createQuery("SELECT w FROM Wine w WHERE w.name = :name", Wine.class).setParameter("name", name).getResultList();
    }

    @Override
    public List<Wine> findWinesByVintage(Year vintage) {
        return em.createQuery("SELECT w FROM Wine w WHERE w.vintage = :vintage", Wine.class).setParameter("vintage", vintage).getResultList();
    }

    @Override
    public List<Wine> findWinesByPredicate(String predicate) {
        return em.createQuery("SELECT w FROM Wine w WHERE w.predicate = :predicate", Wine.class).setParameter("predicate", predicate).getResultList();
    }

    @Override
    public List<Wine> findWinesByPredicateEquivalent(String predicateEquivalent) {
        return em.createQuery("SELECT w FROM Wine w WHERE w.predicateEquivalent = :predicateEquivalent", Wine.class).setParameter("predicateEquivalent", predicateEquivalent).getResultList();
    }

    @Override
    public List<Wine> findWinesByAlcoholVolume(BigDecimal from, BigDecimal to) {
        return em.createQuery("SELECT w FROM Wine w WHERE w.alcoholVolume BETWEEN :fromVolume" + " AND :toVolume", Wine.class).setParameter("fromVolume", from).setParameter("toVolume", to).getResultList();
    }

    @Override
    public List<Wine> findWinesByResidualSugar(BigDecimal from, BigDecimal to) {
        return em.createQuery("SELECT w FROM Wine w WHERE w.alcoholVolume BETWEEN :fromSugar" + " AND :toSugar", Wine.class).setParameter("fromSugar", from).setParameter("toSugar", to).getResultList();
    }

    @Override
    public List<Wine> findWinesByAcidity(BigDecimal from, BigDecimal to) {
        return em.createQuery("SELECT w FROM Wine w WHERE w.alcoholVolume BETWEEN :fromAcidity" + " AND :toAcidity", Wine.class).setParameter("fromAcidity", from).setParameter("toAcidity", to).getResultList();
    }

    @Override
    public List<Wine> findWinesByGrapeSugarContent(BigDecimal from, BigDecimal to) {
        return em.createQuery("SELECT w FROM Wine w WHERE w.alcoholVolume BETWEEN :fromSugar" + " AND :toSugar", Wine.class).setParameter("fromSugar", from).setParameter("toSugar", to).getResultList();
    }

    @Override
    public List<Wine> findWinesBetweenYears(Year from, Year to) {
        return em.createQuery("SELECT w FROM Wine w WHERE w.alcoholVolume BETWEEN :fromYear" + " AND :toYear", Wine.class).setParameter("fromYear", from).setParameter("toYear", to).getResultList();
    }

    @Override
    public Wine findWineByBatch(String batch) {
        return em.createQuery("SELECT w FROM Wine w WHERE w.batch = :batch", Wine.class).setParameter("batch", batch).getSingleResult();
    }

}
