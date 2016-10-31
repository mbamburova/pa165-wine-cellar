package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Tomas Gordian on 10/31/2016.
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PackingDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PackingDao packingDao;

    @Autowired
    private PriceDao priceDao;

    private Packing packing1;
    private Price price;

    @BeforeMethod
    public void setup() {

        packing1 = new Packing();
        packing1.setValidFrom(new LocalDateTime(2015,2,1,0,0));
        packing1.setValidTo(new LocalDateTime(2016,2,1,0,0));
        packing1.setVolume(new BigDecimal("1"));

        price = new Price();
        price.setCurrency(Currency.getInstance("EUR"));
        price.setPrice(new BigDecimal("100"));
        priceDao.createPrice(price);

        packing1.addPrice(price);

        packingDao.createPacking(packing1);
    }

    @Test
    public void testCreate() {
        Packing packing = packingDao.findById(packing1.getId());
        assertThat(packing).isEqualTo(packing1);
    }

    @Test
    public void update() {
        Packing packing = packingDao.findById(packing1.getId());
        packing.setVolume(new BigDecimal("2"));
        packingDao.updatePacking(packing);

        assertThat(packingDao.findById(packing.getId())).isEqualTo(packing);
    }

    @Test(expectedExceptions =  java.lang.AssertionError.class)
    public void updateWithNullVolume() {
        Packing packing = packingDao.findById(packing1.getId());
        packing.setVolume(null);
        packingDao.updatePacking(packing);

        assertThat(packingDao.findById(packing.getId())).isEqualToComparingFieldByField(packing);
    }

    @Test(expectedExceptions = java.lang.AssertionError.class)
    public void updateWithNullValidTo() {
        Packing packing = packingDao.findById(packing1.getId());
        packing.setValidTo(null);
        packingDao.updatePacking(packing);

        assertThat(packingDao.findById(packing.getId())).isEqualToComparingFieldByField(packing);
    }

    @Test(expectedExceptions = java.lang.AssertionError.class)
    public void updateWithNullValidFrom() {
        Packing packing = packingDao.findById(packing1.getId());
        packing.setValidFrom(null);
        packingDao.updatePacking(packing);

        assertThat(packingDao.findById(packing.getId())).isEqualToComparingFieldByField(packing);

    }

    @Test
    public void delete() {
        assertThat(packingDao.findById(packing1.getId())).isNotNull();
        packingDao.deletePacking(packing1);
        assertThat(packingDao.findById(packing1.getId())).isNull();
    }
}