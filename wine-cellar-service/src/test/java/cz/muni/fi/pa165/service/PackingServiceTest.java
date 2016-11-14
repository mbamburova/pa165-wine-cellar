package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.PackingDao;
import cz.muni.fi.pa165.entity.Packing;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.*;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class PackingServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private PackingDao packingDao;

    @Autowired
    @InjectMocks
    private PackingService packingService;

    @Autowired
    private WineService wineService;

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown(){

    }

    @Test
    public void createPacking(){

    }

    @Test
    public void findPackingById(){

    }

    @Test
    public void findAllpackings(){

    }

    @Test
    public void updatePacking(){

    }
/*
    @Test
    public void updateVolume() throws Exception {

    }

    @Test
    public void updateValidFrom() throws Exception {

    }

    @Test
    public void updateValidTo() throws Exception {

    }
*/
    @Test
    public void deletePacking() throws Exception {

    }

    @Test
    public void packingIsValid() throws Exception {

    }

    @Test
    public void addWine() throws Exception {

    }
/*
    @Test
    public void removeWine() throws Exception {

    }
*/
}