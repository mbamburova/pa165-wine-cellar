package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
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
public class PackingDaoTest {

    @Autowired
    private PackingDao packingDao;
    private Packing packing1;
    private Price price;

    @BeforeClass
    public void setup() {

        packing1 = new Packing();
        packing1.setValidFrom(new DateTime(2015-02-04));
        packing1.setValidTo(new DateTime(2016-02-04));
        packing1.setVolume(new BigDecimal(1));

        price = new Price();
        price.setCurrency(Currency.getInstance("EUR"));

        packing1.addPrice(price);
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
}
