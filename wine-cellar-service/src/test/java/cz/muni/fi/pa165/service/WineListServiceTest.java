package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.WineBuilder;
import cz.muni.fi.pa165.dao.WineListDao;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.entity.WineList;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.Year;

import static org.mockito.Mockito.verify;

/**
 * @author MarekScholtz
 * @version 2016.11.25
 */
public class WineListServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private WineListDao WineListDao;

    @InjectMocks
    private WineService wineService;

    @InjectMocks
    private WineListService WineListService;

    private Wine veltlinskeZelene;
    private Wine muskatMoravsky;

    private WineBuilder veltlinskeZelene() {
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

    private WineBuilder muskatMoravsky() {
        return new WineBuilder()
                .name("Muškát moravský")
                .vintage(Year.of(2015))
                .batch("1/14")
                .predicate("kabinetní víno")
                .predicateEquivalent("suché")
                .description("Víno zlatavé barvy s ovocnou vůní citrusových plodů a muškátového oříšku. V chuti nabízí ovocné tóny grapefruitu a zralého citrónu. Ovocnou chuť provází příjemná kyselinka, díky níž je víno pikantní se suchým závěrem.")
                .notes("20,2°ČNM")
                .alcoholVolume(new BigDecimal(12))
                .residualSugar(new BigDecimal(0.7))
                .acidity(new BigDecimal(6.1))
                .grapeSugarContent(new BigDecimal(0));
    }

    @BeforeClass
    public void setUpMock(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        veltlinskeZelene = veltlinskeZelene().build();
        muskatMoravsky = muskatMoravsky().build();
    }

    @Test
    public void createWineList() throws Exception {
    }

    @Test
    public void updateWineList() throws Exception {

    }

    @Test
    public void findWineListById() throws Exception {

    }

    @Test
    public void deleteWineList() throws Exception {

    }

    @Test
    public void findAllWineLists() throws Exception {

    }
}
