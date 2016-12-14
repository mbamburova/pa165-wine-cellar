package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.config.PersistenceApplicationContext;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.entity.Wine;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Currency;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Michaela Bamburová on 25.10.2016.
 */
@ContextConfiguration(classes= PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PriceDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    public PriceDao priceDao;

    @Inject
    private PackingDao packingDao;

    @Inject
    private WineDao wineDao;

    @Inject
    public MarketingEventDao marketingEventDao;

    private Packing packing1;
    private Price price1;
    private Price price2;
    private Price price3;
    private Wine muskatMoravsky;
    private MarketingEvent marketingEvent1;
    private MarketingEvent marketingEvent2;

    private WineBuilder muskatMoravsky() {
        return new WineBuilder()
            .name("Muškát moravský")
            .vintage(Year.of(2015))
            .batch("1/14")
            .predicate("kabinetní víno")
            .predicateEquivalent("suché")
            .description("Víno zlatavé barvy s ovocnou vůní citrusových plodů a muškátového oříšku." +
                " V chuti nabízí ovocné tóny grapefruitu a zralého citrónu. Ovocnou chuť provází příjemná kyselinka," +
                " díky níž je víno pikantní se suchým závěrem.")
            .notes("20,2°ČNM")
            .alcoholVolume(new BigDecimal("12"))
            .residualSugar(new BigDecimal("0.7"))
            .acidity(new BigDecimal("6.1"))
            .grapeSugarContent(new BigDecimal("0"));
    }

    @BeforeMethod
    public void createPrices() {
        muskatMoravsky = muskatMoravsky().build();
        wineDao.createWine(muskatMoravsky);

        packing1 = new Packing();
        packing1.setValidFrom(LocalDateTime.of(2015,2,1,0,0));
        packing1.setValidTo(LocalDateTime.of(2016,2,1,0,0));
        packing1.setVolume(new BigDecimal("1"));
        packing1.setWine(muskatMoravsky);


        packingDao.createPacking(packing1);


        price1 = new Price();
        price2 = new Price();
        price3 = new Price();

        marketingEvent1 = new MarketingEvent();
        marketingEvent1.setDescription("event 1");

        marketingEvent2 = new MarketingEvent();
        marketingEvent2.setDescription("event 2");

        price1.setCurrency(Currency.getInstance("CZK"));
        price1.setPrice(new BigDecimal("140"));
        price1.setMarketingEvent(marketingEvent1);
        price1.setPacking(packing1);

        price2.setCurrency(Currency.getInstance("CZK"));
        price2.setPrice(new BigDecimal("100"));
        price2.setMarketingEvent(marketingEvent1);

        price2.setPacking(packing1);

        price3.setCurrency(Currency.getInstance("EUR"));
        price3.setPrice(new BigDecimal("4"));
        price3.setMarketingEvent(marketingEvent2);

        price3.setPacking(packing1);

        marketingEventDao.createMarketingEvent(marketingEvent1);
        marketingEventDao.createMarketingEvent(marketingEvent2);

        priceDao.createPrice(price1);
        priceDao.createPrice(price2);
        priceDao.createPrice(price3);
    }

    @Test
    public void testCreatePrice() {
        Price price = priceDao.findPriceById(price1.getId());
        assertThat(price).isEqualToComparingFieldByField(price1);
    }

    @Test
    public void testFindAll() {
        List<Price> prices = priceDao.findAllPrices();
        assertThat(prices.size()).isEqualTo(3);
    }

    @Test
    public void testFindById() {
        Price price = priceDao.findPriceById(price1.getId());

        assertThat(price.getCurrency()).isEqualToComparingFieldByField(Currency.getInstance("CZK"));
        assertThat(price.getMarketingEvent()).isEqualToComparingFieldByField(marketingEvent1);
        assertThat(price.getPrice()).isEqualToComparingFieldByField(new BigDecimal("140"));
    }

    @Test
    public void testDeletePrice() {
        assertThat(priceDao.findPriceById(price2.getId())).isNotNull();
        priceDao.deletePrice(price2);

        assertThat(priceDao.findPriceById(price2.getId())).isNull();
    }

    @Test
    public void testUpdatePrice() {
        Price price = priceDao.findPriceById(price3.getId());
        price.setCurrency(Currency.getInstance("CZK"));

        priceDao.updatePrice(price);

        assertThat(priceDao.findPriceById(price.getId())).isEqualToComparingFieldByField(price);
    }

    @Test
    public void testPricesByMarketingEvent1() {
        List<Price> prices = priceDao.findPricesByMarketingEvent(marketingEvent1);

        assertThat(prices.size()).isEqualTo(2);
    }

    @Test
    public void testPricesByMarketingEvent2() {
        List<Price> prices = priceDao.findPricesByMarketingEvent(marketingEvent2);

        assertThat(prices.size()).isEqualToComparingFieldByField(1);
    }

    @Test
    public void testPricesByCurrency() {
        List<Price> prices = priceDao.findPricesByCurrency(Currency.getInstance("CZK"));

        assertThat(prices.size()).isEqualTo(2);
    }

    @Test
    public void testPricesByPrice() {
        List<Price> prices = priceDao.findPricesByPriceAttribute(new BigDecimal("100"));

        assertThat(prices.size()).isEqualTo(1);
    }

    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void testCreatePriceWithNullCurrency(){
        Price priceWithNullCurrency = new Price();
        priceWithNullCurrency.setPrice(new BigDecimal("155"));
        priceWithNullCurrency.setCurrency(null);
        priceDao.createPrice(priceWithNullCurrency);
    }

    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void testCreatePriceWithNullPrice() {
        Price priceWithNullPrice = new Price();
        priceWithNullPrice.setPrice(null);
        priceWithNullPrice.setCurrency(Currency.getInstance("EUR"));
        priceDao.createPrice(priceWithNullPrice);
    }
}
