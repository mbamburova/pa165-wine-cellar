package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

/**
 * @author Tomas Gordian on 10/30/2016.
 */
@Repository
public class PriceDaoImpl implements PriceDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createPrice(Price price) {
        entityManager.persist(price);
    }

    @Override
    public void deletePrice(Price price) {
        entityManager.remove(findPriceById(price.getId()));
    }

    @Override
    public void updatePrice(Price price) {
        entityManager.merge(price);
    }

    @Override
    public Price findPriceById(Long id) {
        return entityManager.find(Price.class, id);
    }

    @Override
    public List<Price> findAllPrices() {
        return Collections.unmodifiableList(
                entityManager.createQuery("SELECT pl FROM Price pl", Price.class).getResultList());
    }

    @Override
    public List<Price> findPricesByPriceAttribute(BigDecimal price) {

        if (price == null)
            throw new IllegalArgumentException("Cannot search for null price");
        try {
            return entityManager.createQuery("select p from Price p where p.price = :price",
                            Price.class).setParameter("price", price).getResultList();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Price> findPricesByCurrency(Currency currency) {

        if (currency == null)
            throw new IllegalArgumentException("Cannot search for null currency");
        try {
            return entityManager.createQuery("select p from Price p where p.currency = :currency",
                    Price.class).setParameter("currency", currency).getResultList();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Price> findPricesByMarketingEvent(MarketingEvent marketingEvent) {
        if (marketingEvent == null)
            throw new IllegalArgumentException("Cannot search for null marketingEvent");
        try {
            return entityManager.createQuery("select p from Price p where p.marketingEvent = :marketingEvent",
                    Price.class).setParameter("marketingEvent", marketingEvent).getResultList();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Price> findPricesByPacking(Packing packing) {
        if (packing == null)
            throw new IllegalArgumentException("Cannot search for null marketingEvent");
        try {
            return entityManager.createQuery("select p from Price p where p.packing = :packing",
                    Price.class).setParameter("packing", packing).getResultList();
        } catch (NoResultException nrf) {
            return null;
        }
    }
}
