package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Price;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Tomas on 10/30/2016.
 */
public class PriceDaoImpl implements PriceDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createPrice(Price price) {
        entityManager.persist(price);
    }

    @Override
    public void deletePrice(Price price) {
        entityManager.remove(price);
    }

    @Override
    public void updatePrice(Price price) {
        entityManager.merge(price);
    }

    @Override
    public void get(Long id) {
        entityManager.find(Price.class, id);
    }
}
