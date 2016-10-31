package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

/**
 * @author Michaela Bamburová on 25.10.2016.
 */
@ContextConfiguration(classes= PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PriceDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public PriceDao priceDao;

    @Autowired
    public MarketingEventDao marketingEventDao;

    private Price price1;
    private Price price2;
    private Price price3;
    private MarketingEvent marketingEvent1;
    private MarketingEvent marketingEvent2;

    @BeforeMethod
    public void createPrices() {
        price1 = new Price();
        price2 = new Price();

        marketingEvent1 = new MarketingEvent();
        marketingEvent1.setDescription("event 1");

        marketingEvent2 = new MarketingEvent();
        marketingEvent2.setDescription("event 2");

        price1.setCurrency(Currency.getInstance("CZK"));
        price1.setPrice(new BigDecimal("140"));
        price1.setMarketingEvent(marketingEvent1);

        price2.setCurrency(Currency.getInstance("CZK"));
        price2.setPrice(new BigDecimal("100"));
        price2.setMarketingEvent(marketingEvent1);

        price3.setCurrency(Currency.getInstance("EUR"));
        price3.setPrice(new BigDecimal("4"));

        marketingEventDao.create(marketingEvent1);
        marketingEventDao.create(marketingEvent2);

        priceDao.createPrice(price1);
        priceDao.createPrice(price2);
        priceDao.createPrice(price3);
    }

    @Test
    public void findPriceTest() {

        Price price = priceDao.get(price1.getId());
        Assert.assertEquals(price.getCurrency(), Currency.getInstance("CZK") );
        Assert.assertEquals(price.getMarketingEvent(), marketingEvent1);
        Assert.assertEquals(price.getPrice(), BigDecimal.valueOf(140L));
    }

    @Test
    public void deletePriceTest() {
        Assert.assertNotEquals(priceDao.get(price2.getId()));
        priceDao.deletePrice(price2);
        Assert.assertNull(priceDao.get(price2.getId()));
    }

    @Test
    public void updatePriceTest() {
        Price price = priceDao.get(price3.getId());
        price.setCurrency(Currency.getInstance("CZK"));

        priceDao.updatePrice(price3);

    }



}
