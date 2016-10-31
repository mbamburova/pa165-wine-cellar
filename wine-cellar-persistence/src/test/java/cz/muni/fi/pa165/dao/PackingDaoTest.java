package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Packing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * @author Tomas Gordian on 10/31/2016.
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PackingDaoTest {

    @Autowired
    private PackingDao packingDao;
    private Packing packing;

    @BeforeClass
    public void setup() {

        packing = new Packing();
        packing.setValidFrom();
        packing.setValidTo();
        packing.setVolume(new BigDecimal(1));
    }

    @Test
    public void create() {

    }
}
