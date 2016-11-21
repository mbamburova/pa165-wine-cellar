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

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Michaela Bamburová on 08.11.2016
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class WineServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private WineDao wineDao;

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
        wineService.create(veltlinskeZelene);
        verify(wineDao).createWine(veltlinskeZelene);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testCreateWineWithNullName() {
        veltlinskeZelene.setName(null);
        doThrow(new WineCellarDataAccessException("Cannot create wine with null name."))
            .when(wineDao)
            .createWine(veltlinskeZelene);

        wineService.create(veltlinskeZelene);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testCreateWineWithNullVintage() {
        muskatMoravsky.setVintage(null);
        doThrow(new WineCellarDataAccessException("Cannot create wine with null vintage."))
            .when(wineDao)
            .createWine(muskatMoravsky);

        wineService.create(muskatMoravsky);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testCreateWineWithNullBatch() {
        svatovavrinecke.setBatch(null);
        doThrow(new WineCellarDataAccessException("Cannot create wine with null batch"))
            .when(wineDao)
            .createWine(svatovavrinecke);

        wineService.create(svatovavrinecke);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testCreateWineWithNegativeAlcoholVolume() {
        veltlinskeZelene.setAlcoholVolume(new BigDecimal("-0.6"));
        doThrow(new WineCellarDataAccessException("Cannot create wine with negative alcohol volume"))
            .when(wineDao)
            .createWine(veltlinskeZelene);

        wineService.create(veltlinskeZelene);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testCreateWineWithNegativeResidualSugar() {
        muskatMoravsky.setResidualSugar(new BigDecimal("-0.1"));
        doThrow(new WineCellarDataAccessException("Cannot create wine with negative residual sugar"))
            .when(wineDao)
            .createWine(muskatMoravsky);

        wineService.create(muskatMoravsky);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testCreateWineWithNegativeAcidity() {
        svatovavrinecke.setAcidity(new BigDecimal("-0.1"));
        doThrow(new WineCellarDataAccessException("Cannot create wine with negative acidity."))
            .when(wineDao)
            .createWine(svatovavrinecke);

        wineService.create(svatovavrinecke);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testCreateWineWithNegativeGrapeSugarContent() {
        veltlinskeZelene.setGrapeSugarContent(new BigDecimal("-0.7"));
        doThrow(new WineCellarDataAccessException("Cannot create wine with negative grape sugar content."))
            .when(wineDao)
            .createWine(veltlinskeZelene);

        wineService.create(veltlinskeZelene);
    }

    @Test
    public void testUpdateWine() {
        wineService.update(muskatMoravsky);
        verify(wineDao).updateWine(muskatMoravsky);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testUpdateWineWithNullName() {
        veltlinskeZelene.setName(null);
        doThrow(new WineCellarDataAccessException("Cannot update wine with null name."))
            .when(wineDao)
            .updateWine(veltlinskeZelene);

        wineService.update(veltlinskeZelene);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testUpdateWineWithNullVintage() {
        muskatMoravsky.setVintage(null);
        doThrow(new WineCellarDataAccessException("Cannot update wine with null vintage."))
            .when(wineDao)
            .updateWine(muskatMoravsky);

        wineService.update(muskatMoravsky);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testUpdateWineWithNullBatch() {
        svatovavrinecke.setBatch(null);
        doThrow(new WineCellarDataAccessException("Cannot update wine with null batch"))
            .when(wineDao)
            .updateWine(svatovavrinecke);

        wineService.update(svatovavrinecke);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testUpdateWineWithNegativeAlcoholVolume() {
        veltlinskeZelene.setAlcoholVolume(new BigDecimal("-0.6"));
        doThrow(new WineCellarDataAccessException("Cannot update wine with negative alcohol volume"))
            .when(wineDao)
            .updateWine(veltlinskeZelene);

        wineService.update(veltlinskeZelene);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testUpdateWineWithNegativeResidualSugar() {
        muskatMoravsky.setResidualSugar(new BigDecimal("-0.1"));
        doThrow(new WineCellarDataAccessException("Cannot update wine with negative residual sugar"))
            .when(wineDao)
            .updateWine(muskatMoravsky);

        wineService.update(muskatMoravsky);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testUpdateWineWithNegativeAcidity() {
        svatovavrinecke.setAcidity(new BigDecimal("-0.1"));
        doThrow(new WineCellarDataAccessException("Cannot update wine with negative acidity."))
            .when(wineDao)
            .updateWine(svatovavrinecke);

        wineService.update(svatovavrinecke);
    }

    @Test(expectedExceptions = WineCellarDataAccessException.class)
    public void testUpdateWineWithNegativeGrapeSugarContent() {
        veltlinskeZelene.setGrapeSugarContent(new BigDecimal("-0.7"));
        doThrow(new WineCellarDataAccessException("Cannot update wine with negative grape sugar content."))
            .when(wineDao)
            .updateWine(veltlinskeZelene);

        wineService.update(veltlinskeZelene);
    }

    @Test
    public void testFindWineById() {
        when(wineDao.findWineById(veltlinskeZelene.getId()))
            .thenReturn(veltlinskeZelene);

        assertThat(wineService.get(veltlinskeZelene.getId()))
            .isEqualToComparingFieldByField(veltlinskeZelene);

        verify(wineDao).findWineById(veltlinskeZelene.getId());
    }


    @Test
    public void testFindWineByNonExistingId() {
        when(wineDao.findWineById(-1L))
            .thenReturn(null);

        assertThat(wineService.get(-1L)).isNull();

        verify(wineDao).findWineById(-1L);
    }

    @Test
    public void testFindAllWines() {
        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);
        expectedWines.add(muskatMoravsky);
        expectedWines.add(svatovavrinecke);

        when(wineDao.findAllWines()).thenReturn(expectedWines);
        List<Wine> actWines = wineService.findAll();

        assertThat(actWines.size()).isEqualTo(expectedWines.size());
        for(int i = 0; i < expectedWines.size(); i++) {
            assertThat(actWines.get(i)).isEqualToComparingFieldByField(expectedWines.get(i));
        }

        verify(wineDao).findAllWines();
    }

    @Test
    public void testFindWinesByName() {
        String name = "Veltlínske zelené";

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);

        when(wineDao.findByName(name)).thenReturn(expectedWines);
        assertThat(wineService.findByName(name).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findByName(name);
    }

    @Test
    public void testFindWinesByVintage() {
        Year year = Year.of(2015);

        List<Wine> expectedWines = new ArrayList<>();

        expectedWines.add(muskatMoravsky);
        expectedWines.add(svatovavrinecke);

        when(wineDao.findByVintage(year)).thenReturn(expectedWines);
        assertThat(wineService.findByVintage(year).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findByVintage(year);
    }

    @Test
    public void testFindWineByBatch() {
        String batch = "6/15";

        when(wineDao.findByBatch(batch)).thenReturn(svatovavrinecke);
        assertThat(wineService.findByBatch(batch)).isEqualToComparingFieldByField(svatovavrinecke);

        verify(wineDao).findByBatch(batch);
    }

    @Test
    public void testFindWinesByPredicate() {
        String predicate = "kabinetní víno";

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);
        expectedWines.add(muskatMoravsky);

        when(wineDao.findByPredicate(predicate)).thenReturn(expectedWines);
        assertThat(wineService.findByPredicate(predicate).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findByPredicate(predicate);
    }

    @Test
    public void testFindWinesByPredicateEquivalent() {
        String predicateEquivalent = "suché";

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);
        expectedWines.add(muskatMoravsky);
        expectedWines.add(svatovavrinecke);

        when(wineDao.findByPredicateEquivalent(predicateEquivalent)).thenReturn(expectedWines);
        assertThat(wineService.findByPredicateEquivalent(predicateEquivalent).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findByPredicateEquivalent(predicateEquivalent);
    }

    @Test
    public void testFindWinesByDescription() {
        String description = "Elegantní, svěží víno s lehkou aromatikou angreštu a zeleného pepře. " +
            "Chuťový vjem je tvořen pikantní kyselinkou a kořenito-ovocnými tóny.";

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);

        when(wineDao.findByDescription(description)).thenReturn(expectedWines);
        assertThat(wineService.findByDescription(description).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findByDescription(description);
    }

    @Test
    public void testFindWinesByNotes() {
        String notes = "30,2°ČNM";

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(svatovavrinecke);

        when(wineDao.findByNotes(notes)).thenReturn(expectedWines);
        assertThat(wineService.findByNotes(notes).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findByNotes(notes);
    }

    @Test
    public void testFindWinesByAlcoholVolume() {
        BigDecimal alcoholVolume = new BigDecimal("12");

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(svatovavrinecke);
        expectedWines.add(muskatMoravsky);

        when(wineDao.findByAlcoholVolume(alcoholVolume)).thenReturn(expectedWines);
        assertThat(wineService.findByAlcoholVolume(alcoholVolume).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findByAlcoholVolume(alcoholVolume);
    }

    @Test
    public void testFindWinesByResidualSugar() {
        BigDecimal residualSugar = new BigDecimal("0.7");

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(muskatMoravsky);

        when(wineDao.findByResidualSugar(residualSugar)).thenReturn(expectedWines);
        assertThat(wineService.findByResidualSugar(residualSugar).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findByResidualSugar(residualSugar);
    }

    @Test
    public void testFindWinesByAcidity() {
        BigDecimal acidity = new BigDecimal("4.6");

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(svatovavrinecke);

        when(wineDao.findByAcidity(acidity)).thenReturn(expectedWines);
        assertThat(wineService.findByAcidity(acidity).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findByAcidity(acidity);
    }

    @Test
    public void testFindWinesByGrapeSugarContent() {
        BigDecimal grapeSugarContent = new BigDecimal("4.6");

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(svatovavrinecke);
        expectedWines.add(veltlinskeZelene);
        expectedWines.add(muskatMoravsky);

        when(wineDao.findByGrapeSugarContent(grapeSugarContent)).thenReturn(expectedWines);
        assertThat(wineService.findByGrapeSugarContent(grapeSugarContent).size()).isEqualTo(expectedWines.size());

        verify(wineDao).findByGrapeSugarContent(grapeSugarContent);
    }

    @Test
    public void testDeleteWine() {
        wineService.delete(veltlinskeZelene);
        verify(wineDao).deleteWine(veltlinskeZelene);
    }
}
