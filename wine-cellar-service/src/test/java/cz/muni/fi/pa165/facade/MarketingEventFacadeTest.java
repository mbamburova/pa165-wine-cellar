package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.MarketingEventDao;
import cz.muni.fi.pa165.service.MarketingEventService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import static org.junit.Assert.*;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class MarketingEventFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private MarketingEventDao marketingEventDao;

    @Autowired
    @InjectMocks
    private MarketingEventService marketingEventService;


    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){

    }

    @Test
    public void create(){

    }

    @Test
    public void delete(){

    }

    @Test
    public void update(){

    }


    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void findByDescription() throws Exception {

    }

    @Test
    public void addPrice() throws Exception {

    }

    @Test
    public void removePrice() throws Exception {

    }

}