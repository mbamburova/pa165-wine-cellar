package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.WineBuilder;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.WineDao;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.exception.WineCellarDataAccessException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Michaela Bamburová on 08.11.2016
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class WineServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private WineDao wineDao;

    @Inject
    @InjectMocks
    private WineService wineService;

    private Wine veltlinskeZelene;
    private Wine muskatMoravsky;
    private Wine svatovavrinecke;

    private WineBuilder veltlinskeZelene() {
        return new WineBuilder()
            .name("Veltlínske zelené")
            .vintage(Year.of(2014))
            .batch("10/14")
            .predicate("kabinetní víno")
            .predicateEquivalent("suché")
            .description("Elegantní, svěží víno s lehkou aromatikou angreštu a zeleného pepře. " +
                "Chuťový vjem je tvořen pikantní kyselinkou a kořenito-ovocnými tóny.")
            .notes("20,0°ČNM")
            .alcoholVolume(new BigDecimal("10.94"))
            .residualSugar(new BigDecimal("2.8"))
            .acidity(new BigDecimal("7.5"))
            .grapeSugarContent(new BigDecimal("0"));
    }

    private WineBuilder muskatMoravsky() {
        return new WineBuilder()
            .name("Muškát moravský")
            .vintage(Year.of(2015))
            .batch("1/14")
            .predicate("kabinetní víno")
            .predicateEquivalent("suché")
            .description("Víno zlatavé barvy s ovocnou vůní citrusových plodů a muškátového oříšku." +
                " V chuti nabízí ovocné tóny grapefruitu a zralého citrónu. Ovocnou chuť provází příjemná kyselinka," +
                " díky níž je víno pikantní se suchým závěrem.")
            .notes("20,2°ČNM")
            .alcoholVolume(new BigDecimal("12"))
            .residualSugar(new BigDecimal("0.7"))
            .acidity(new BigDecimal("6.1"))
            .grapeSugarContent(new BigDecimal("0"));
    }

    private WineBuilder svatovavrinecke() {
        return new WineBuilder()
            .name("Svatovavřinecké")
            .vintage(Year.of(2015))
            .batch("6/15")
            .predicate("pozdní sběr")
            .predicateEquivalent("suché")
            .description("Jiskrné víno rubínových odstínů barvy. Kořenitá vůně višní a třešňové kůry. " +
                "Zabalená v nádechu kouře z dubového dřeva. Chuť charakterní pevná, v níž se snoubí tóny višní," +
                " svěží kyselinky a příjemného třísla.")
            .notes("30,2°ČNM")
            .alcoholVolume(new BigDecimal("12"))
            .residualSugar(new BigDecimal("6.2"))
            .acidity(new BigDecimal("4.6"))
            .grapeSugarContent(new BigDecimal("0"));
    }

    @BeforeClass
    public void setUpMock(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        veltlinskeZelene = veltlinskeZelene().build();
        muskatMoravsky = muskatMoravsky().build();
        svatovavrinecke = svatovavrinecke().build();
    }

    @Test
    public void testCreateWine() {
        wineService.createWine(veltlinskeZelene);
        verify(wineDao).createWine(veltlinskeZelene);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testCreateWineWithNullName() {
        veltlinskeZelene.setName(null);
        doThrow(new WineCellarDataAccessException("Cannot create wine with null name."))
            .when(wineDao)
            .createWine(veltlinskeZelene);

        wineService.createWine(veltlinskeZelene);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testCreateWineWithNullBatch() {
        svatovavrinecke.setBatch(null);
        doThrow(new WineCellarDataAccessException("Cannot create wine with null batch"))
            .when(wineDao)
            .createWine(svatovavrinecke);

        wineService.createWine(svatovavrinecke);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testCreateWineWithNegativeAlcoholVolume() {
        veltlinskeZelene.setAlcoholVolume(new BigDecimal("-0.6"));
        doThrow(new WineCellarDataAccessException("Cannot create wine with negative alcohol volume"))
            .when(wineDao)
            .createWine(veltlinskeZelene);

        wineService.createWine(veltlinskeZelene);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testCreateWineWithNegativeResidualSugar() {
        muskatMoravsky.setResidualSugar(new BigDecimal("-0.1"));
        doThrow(new WineCellarDataAccessException("Cannot create wine with negative residual sugar"))
            .when(wineDao)
            .createWine(muskatMoravsky);

        wineService.createWine(muskatMoravsky);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testCreateWineWithNegativeAcidity() {
        svatovavrinecke.setAcidity(new BigDecimal("-0.1"));
        doThrow(new WineCellarDataAccessException("Cannot create wine with negative acidity."))
            .when(wineDao)
            .createWine(svatovavrinecke);

        wineService.createWine(svatovavrinecke);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testCreateWineWithNegativeGrapeSugarContent() {
        veltlinskeZelene.setGrapeSugarContent(new BigDecimal("-0.7"));
        doThrow(new WineCellarDataAccessException("Cannot create wine with negative grape sugar content."))
            .when(wineDao)
            .createWine(veltlinskeZelene);

        wineService.createWine(veltlinskeZelene);
    }

    @Test
    public void testUpdateWine() {
        wineService.updateWine(muskatMoravsky);
        verify(wineDao).updateWine(muskatMoravsky);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testUpdateWineWithNullName() {
        veltlinskeZelene.setName(null);
        doThrow(new WineCellarDataAccessException("Cannot update wine with null name."))
            .when(wineDao)
            .updateWine(veltlinskeZelene);

        wineService.updateWine(veltlinskeZelene);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testUpdateWineWithNullBatch() {
        svatovavrinecke.setBatch(null);
        doThrow(new WineCellarDataAccessException("Cannot update wine with null batch"))
            .when(wineDao)
            .updateWine(svatovavrinecke);

        wineService.updateWine(svatovavrinecke);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testUpdateWineWithNegativeAlcoholVolume() {
        veltlinskeZelene.setAlcoholVolume(new BigDecimal("-0.6"));
        doThrow(new WineCellarDataAccessException("Cannot update wine with negative alcohol volume"))
            .when(wineDao)
            .updateWine(veltlinskeZelene);

        wineService.updateWine(veltlinskeZelene);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testUpdateWineWithNegativeResidualSugar() {
        muskatMoravsky.setResidualSugar(new BigDecimal("-0.1"));
        doThrow(new WineCellarDataAccessException("Cannot update wine with negative residual sugar"))
            .when(wineDao)
            .updateWine(muskatMoravsky);

        wineService.updateWine(muskatMoravsky);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testUpdateWineWithNegativeAcidity() {
        svatovavrinecke.setAcidity(new BigDecimal("-0.1"));
        doThrow(new WineCellarDataAccessException("Cannot update wine with negative acidity."))
            .when(wineDao)
            .updateWine(svatovavrinecke);

        wineService.updateWine(svatovavrinecke);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testUpdateWineWithNegativeGrapeSugarContent() {
        veltlinskeZelene.setGrapeSugarContent(new BigDecimal("-0.7"));
        doThrow(new WineCellarDataAccessException("Cannot update wine with negative grape sugar content."))
            .when(wineDao)
            .updateWine(veltlinskeZelene);

        wineService.updateWine(veltlinskeZelene);
    }

    @Test
    public void testFindWineById() {
        when(wineDao.findWineById(veltlinskeZelene.getId()))
            .thenReturn(veltlinskeZelene);

        assertThat(wineService.findWineById(veltlinskeZelene.getId()))
            .isEqualToComparingFieldByField(veltlinskeZelene);

        verify(wineDao).findWineById(veltlinskeZelene.getId());
    }


    @Test
    public void testFindWineByNonExistingId() {
        when(wineDao.findWineById(-1L))
            .thenReturn(null);

        assertThat(wineService.findWineById(-1L)).isNull();

        verify(wineDao).findWineById(-1L);
    }

    @Test
    public void testFindAllWines() {
        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);
        expectedWines.add(muskatMoravsky);
        expectedWines.add(svatovavrinecke);

        when(wineDao.findAllWines()).thenReturn(expectedWines);
        List<Wine> actWines = wineService.findAllWines();

        assertThat(actWines.size()).isEqualTo(expectedWines.size());

        verify(wineDao).findAllWines();
    }

    @Test
    public void testFindWinesByName() {
        String name = "Veltlínske zelené";

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);

        when(wineDao.findWinesByName(name)).thenReturn(expectedWines);
        assertThat(wineService.findWinesByName(name).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findWinesByName(name);
    }

    @Test
    public void testFindWinesByVintage() {
        Year year = Year.of(2015);

        List<Wine> expectedWines = new ArrayList<>();

        expectedWines.add(muskatMoravsky);
        expectedWines.add(svatovavrinecke);

        when(wineDao.findWinesByVintage(year)).thenReturn(expectedWines);
        assertThat(wineService.findWinesByVintage(year).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findWinesByVintage(year);
    }

    @Test
    public void testFindWineByBatch() {
        String batch = "6/15";

        when(wineDao.findWineByBatch(batch)).thenReturn(svatovavrinecke);
        assertThat(wineService.findWineByBatch(batch)).isEqualToComparingFieldByField(svatovavrinecke);

        verify(wineDao).findWineByBatch(batch);
    }

    @Test
    public void testFindWinesByPredicate() {
        String predicate = "kabinetní víno";

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);
        expectedWines.add(muskatMoravsky);

        when(wineDao.findWinesByPredicate(predicate)).thenReturn(expectedWines);
        assertThat(wineService.findWinesByPredicate(predicate).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findWinesByPredicate(predicate);
    }

    @Test
    public void testFindWinesByPredicateEquivalent() {
        String predicateEquivalent = "suché";

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);
        expectedWines.add(muskatMoravsky);
        expectedWines.add(svatovavrinecke);

        when(wineDao.findWinesByPredicateEquivalent(predicateEquivalent)).thenReturn(expectedWines);
        assertThat(wineService.findWinesByPredicateEquivalent(predicateEquivalent).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findWinesByPredicateEquivalent(predicateEquivalent);
    }

    @Test
    public void testFindWinesByAlcoholVolume() {
        BigDecimal from = new BigDecimal("10");
        BigDecimal to = new BigDecimal("12");

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);
        expectedWines.add(muskatMoravsky);
        expectedWines.add(svatovavrinecke);

        when(wineDao.findWinesByAlcoholVolume(from, to)).thenReturn(expectedWines);
        assertThat(wineService.findWinesByAlcoholVolume(from, to).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findWinesByAlcoholVolume(from, to);
    }

    @Test
    public void testFindWinesByResidualSugar() {
        BigDecimal from = new BigDecimal("2.8");
        BigDecimal to = new BigDecimal("2.8");

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);

        when(wineDao.findWinesByResidualSugar(from, to)).thenReturn(expectedWines);
        assertThat(wineService.findWinesByResidualSugar(from, to).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findWinesByResidualSugar(from, to);
    }

    @Test
    public void testFindWinesByAcidity() {
        BigDecimal from = new BigDecimal("6");
        BigDecimal to = new BigDecimal("7");

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);
        expectedWines.add(muskatMoravsky);

        when(wineDao.findWinesByAcidity(from, to)).thenReturn(expectedWines);
        assertThat(wineService.findWinesByAcidity(from, to).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findWinesByAcidity(from, to);
    }

    @Test
    public void testFindWinesByGrapeSugarContent() {
        BigDecimal from = new BigDecimal("0");
        BigDecimal to = new BigDecimal("0");

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);
        expectedWines.add(muskatMoravsky);
        expectedWines.add(svatovavrinecke);

        when(wineDao.findWinesByGrapeSugarContent(from, to)).thenReturn(expectedWines);
        assertThat(wineService.findWinesByGrapeSugarContent(from, to).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findWinesByGrapeSugarContent(from, to);
    }

    @Test
    public void testFindWinesBetweenYears() {
        Year from = Year.of(2014);
        Year to = Year.of(2016);

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);
        expectedWines.add(muskatMoravsky);
        expectedWines.add(svatovavrinecke);

        when(wineDao.findWinesBetweenYears(from, to)).thenReturn(expectedWines);
        assertThat(wineService.findWinesBetweenYears(from, to).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findWinesBetweenYears(from, to);
    }

    @Test
    public void testDeleteWine() {
        wineService.deleteWine(veltlinskeZelene);
        verify(wineDao).deleteWine(veltlinskeZelene);
    }
}
