package cz.muni.fi.pa165.service;

import java.math.BigDecimal;
import java.time.Year;

import cz.muni.fi.pa165.WineBuilder;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.PackingDao;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Wine;
import org.joda.time.LocalDateTime;
import org.junit.After;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

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

    private Packing packing1;
    private Packing packing2;

    private WineBuilder wine() {
        return new WineBuilder()
                .name("Veltlínske zelené")
                .vintage(Year.of(2014))
                .batch("10/14")
                .predicate("kabinetní víno")
                .predicateEquivalent("suché")
                .description("Elegantní, svěží víno s lehkou aromatikou angreštu a zeleného pepře. Chuťový vjem je tvořen pikantní kyselinkou a kořenito-ovocnými tóny.")
                .notes("20,0°ČNM")
                .alcoholVolume(new BigDecimal(10.94))
                .residualSugar(new BigDecimal(2.8))
                .acidity(new BigDecimal(7.5))
                .grapeSugarContent(new BigDecimal(0));
    }

    @BeforeClass
    public void setUpMock(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        Wine wine = wine().build();
        wineService.create(wine);

        packing1 = new Packing();
        packing1.setVolume(new BigDecimal("0.75"));
        packing1.setWine(wine);
        packing1.setValidFrom(new LocalDateTime(2016,10,10,0,0));
        packing1.setValidTo(null);
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