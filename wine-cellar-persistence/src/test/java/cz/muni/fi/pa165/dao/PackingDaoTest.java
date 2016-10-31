package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

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
    private Packing packing1;
    private Packing packing2;
    private Packing packing3;
    private Price price;

    @BeforeClass
    public void setup() {

        packing1 = new Packing();
        packing1.setValidFrom(new DateTime(2015,02,04,0,0));
        packing1.setValidTo(new DateTime(2016,02,04,0,0));
        packing1.setVolume(new BigDecimal(1));

        price = new Price();
        price.setCurrency(Currency.getInstance("EUR"));

        packing1.addPrice(price);

        packing2 = new Packing();
        packing2.setValidFrom(new DateTime(2010,2,8,0,0));
        packing2.setValidTo(new DateTime(2013,1,4,0,0));
        packing2.setVolume(new BigDecimal(0.5));
        packing2.addPrice(price);

        packing3 = new Packing();
        packing3.setValidFrom(new DateTime(2010,2,8,0,0));
        packing3.setValidTo(new DateTime(2016,1,8,0,0));
        packing3.setVolume(new BigDecimal(0.33));
        packing3.addPrice(price);

        packingDao.createPacking(packing1);
        packingDao.createPacking(packing2);
        packingDao.createPacking(packing3);
    }

    @Test
    public void create() {
        packingDao.createPacking(packing1);
        assertThat(packingDao.findById(packing1.getId())).isEqualToComparingFieldByField(packing1);
    }

    @Test
    public void update() {
        packingDao.createPacking(packing1);
        packing1.setVolume(new BigDecimal(0.5));
        packingDao.updatePacking(packing1);
        assertThat(packingDao.findById(packing1.getId())).isEqualToComparingFieldByField(packing1);
    }

    @Test(expectedExceptions = javax.persistence.PersistenceException.class)
    public void updateWithNullName() {
        packingDao.createPacking(packing1);
        packing1.setVolume(null);
        packingDao.updatePacking(packing1);
    }

    @Test(expectedExceptions = javax.persistence.PersistenceException.class)
    public void updateWithNullValidTo() {
        packingDao.createPacking(packing1);
        packing1.setValidTo(null);
        packingDao.updatePacking(packing1);
    }

    @Test(expectedExceptions = javax.persistence.PersistenceException.class)
    public void updateWithNullValidFrom() {
        packingDao.createPacking(packing1);
        packing1.setValidFrom(null);
        packingDao.updatePacking(packing1);
    }

    @Test
    public void delete() {
        packingDao.createPacking(packing1);
        assertThat(packingDao.findById(packing1.getId())).isNotNull();
        packingDao.deletePacking(packing1);
        assertThat(packingDao.findById(packing1.getId())).isNull();
    }

    @Test
    public void findAll() {
        packingDao.createPacking(packing1);
        List<Packing> found = packingDao.findAll();
        Assert.assertEquals(found.size(), 3);
    }
}
