package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
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

//@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MarketingEventDaoTest {



    @Test
    public void createMarketingEvent(){

    }

    @Test
    public void getMarketingEvent(){

    }

    @Test
    public void updateMarketingEvent(){

    }

    @Test
    public void deleteMarketingEvent(){

    }

    @Test
    public void getAllMarketingEvent(){

    }

}