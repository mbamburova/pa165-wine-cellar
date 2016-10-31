package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.entity.Packing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Silvia Borzová
 *         31/10/2016
 */

@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class WineListDaoTest {

    @Autowired
    public PackingDao packingDao;

    @Autowired
    public WineDao wineDao;

    @Autowired
    public

    @Test
    public void createWineList(){

    }

    @Test
    public void getWineList(){

    }

    @Test
    public void updateWineList(){

    }

    @Test
    public void deleteWineList(){

    }

    @Test
    public void getAllWineList(){

    }

}