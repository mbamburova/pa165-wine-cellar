package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

/**
 * @author Michaela Bamburová on 25.10.2016.
 */
//@ContextConfiguration(classes= PersistenceApplicationContext.class)
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
        price2.setMarketingEvent(marketingEvent2);

        marketingEventDao.create(marketingEvent1);
        marketingEventDao.create(marketingEvent2);

        priceDao.createPrice(price1);
        priceDao.createPrice(price2);
        priceDao.createPrice(price3);
    }


    @Test
    public void testFindAll() {
        List<Price> prices = priceDao.findAll();
        assertThat(prices.size()).isEqualToComparingFieldByField(3);
    }

    @Test
    public void testFindPrice() {
        Price price = priceDao.get(price1.getId());

        assertThat(price.getCurrency()).isEqualToComparingFieldByField(Currency.getInstance("CZK"));
        assertThat(price.getMarketingEvent()).isEqualToComparingFieldByField(marketingEvent1);
        assertThat(price.getPrice()).isEqualToComparingFieldByField(140L);
    }

    @Test
    public void testDeletePrice() {
        Assert.assertNotEquals(priceDao.get(price2.getId()));
        priceDao.deletePrice(price2);
        assertThat(priceDao.get(price2.getId())).isNull;
    }

    @Test
    public void testUpdatePrice() {
        Price price = priceDao.get(price3.getId());
        Price price4 = priceDao.get(price2.getId());

        price.setCurrency(Currency.getInstance("CZK"));
        priceDao.updatePrice(price);

        assertThat(priceDao.get(price.getId())).isEqualToComparingFieldByField(price);
    }

    @Test
    public void testPricesByMarketingEvent1() {
        List<Price> prices = priceDao.getByMarketingEvent(marketingEvent1);

        assertThat(prices.size()).isEqualToComparingFieldByField(2);
    }

    @Test
    public void testPricesByMarketingEvent2() {
        List<Price> prices = priceDao.getByMarketingEvent(marketingEvent2);

        assertThat(prices.size()).isEqualToComparingFieldByField(1);
    }

    @Test
    public void testPricesByCurrency() {
        List<Price> prices = priceDao.getByCurrency(Currency.getInstance("CZK"));

        assertThat(prices.size()).isEqualToComparingFieldByField(2);
    }

}
