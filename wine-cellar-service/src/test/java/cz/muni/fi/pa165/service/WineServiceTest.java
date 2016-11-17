package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.WineDao;
import cz.muni.fi.pa165.entity.Wine;
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Michaela Bamburová on 08.11.2016.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class WineServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private WineDao wineDao;

    @Autowired
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
    public void testCreateWine(){

    }

    @Test
    public void testCreateWineWithNullName(){

    }

    @Test
    public void testCreateWineWithNullVintage(){

    }

    @Test
    public void testCreateWineWithNullBatch(){

    }

    @Test
    public void testCreateWineWithNegativeAlcoholVolume(){

    }

    @Test
    public void testCreateWineWithNegativeResidualSugar(){

    }

    @Test
    public void testCreateWineWithNegativeAcidity(){

    }

    @Test
    public void testCreateWineWithNegativeGrapeSugarContent(){

    }

    @Test
    public void testUpdateWine(){

    }

    @Test
    public void testUpdateWineWithNullName(){

    }

    @Test
    public void testUpdateWineWithNullVintage(){

    }

    @Test
    public void testUpdateWineWithNullBatch(){

    }

    @Test
    public void testUpdateWineWithNegativeAlcoholVolume(){

    }

    @Test
    public void testUpdateWineWithNegativeResidualSugar(){

    }

    @Test
    public void testUpdateWineWithNegativeAcidity(){

    }

    @Test
    public void testUpdateWineWithNegativeGrapeSugarContent(){

    }

    @Test
    public void testFindWineById(){

    }


    @Test
    public void testFindWineByNonExistingId(){

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
    }

    @Test
    public void testFindWinesByName() {
        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);

        when(wineDao.findByName("Veltlínske zelené")).thenReturn(expectedWines);
        assertThat(wineService.findByName("Veltlínske zelené").size()).isEqualTo(expectedWines.size());
    }

    @Test
    public void testFindWinesByVintage() {
        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(muskatMoravsky);
        expectedWines.add(svatovavrinecke);

        Year year = Year.of(2015);

        when(wineDao.findByVintage(year)).thenReturn(expectedWines);
        assertThat(wineService.findByVintage(year).size()).isEqualTo(expectedWines.size());
    }

    @Test
    public void testFindWineByBatch() {
        String batch = "6/15";

        when(wineDao.findByBatch(batch)).thenReturn(svatovavrinecke);
        assertThat(wineService.findByBatch(batch)).isEqualTo(svatovavrinecke);
    }

    @Test
    public void testFindWinesByPredicate() {
        String predicate = "kabinetní víno";
        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);
        expectedWines.add(muskatMoravsky);

        when(wineDao.findByPredicate(predicate)).thenReturn(expectedWines);
        assertThat(wineService.findByPredicate(predicate).size()).isEqualTo(expectedWines.size());
    }

    @Test
    public void testFindWinesByPredicateEquivalent() {
        String predicateEquivalent = "suché";

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);
        expectedWines.add(muskatMoravsky);
        expectedWines.add(svatovavrinecke);

        when(wineDao.findByPredicate(predicateEquivalent)).thenReturn(expectedWines);
        assertThat(wineService.findByPredicateEquivalent(predicateEquivalent).size()).isEqualTo(expectedWines.size());
    }

    @Test
    public void testFindWinesByDescription() {
        String description = "Elegantní, svěží víno s lehkou aromatikou angreštu a zeleného pepře. " +
            "Chuťový vjem je tvořen pikantní kyselinkou a kořenito-ovocnými tóny.";

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(veltlinskeZelene);

        when(wineDao.findByDescription(description)).thenReturn(expectedWines);
        assertThat(wineService.findByDescription(description).size()).isEqualTo(expectedWines.size());
    }

    @Test
    public void testFindWinesByNotes() {
        String notes = "30,2°ČNM";

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(svatovavrinecke);

        when(wineDao.findByNotes(notes)).thenReturn(expectedWines);
        assertThat(wineService.findByNotes(notes).size()).isEqualTo(expectedWines.size());
    }

    @Test
    public void testFindWinesByAlcoholVolume() {
        BigDecimal alcoholVolume = new BigDecimal("12");

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(svatovavrinecke);
        expectedWines.add(muskatMoravsky);

        when(wineDao.findByAlcoholVolume(alcoholVolume)).thenReturn(expectedWines);
        assertThat(wineService.findByAlcoholVolume(alcoholVolume).size()).isEqualTo(expectedWines.size());
    }

    @Test
    public void testFindWinesByResidualSugar() {
        BigDecimal residualSugar = new BigDecimal("0.7");

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(muskatMoravsky);

        when(wineDao.findByResidualSugar(residualSugar)).thenReturn(expectedWines);
        assertThat(wineService.findByResidualSugar(residualSugar).size()).isEqualTo(expectedWines.size());
    }

    @Test
    public void testFindWinesByAcidity() {
        BigDecimal acidity = new BigDecimal("4.6");

        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(svatovavrinecke);

        when(wineDao.findByAcidity(acidity)).thenReturn(expectedWines);
        assertThat(wineService.findByAcidity(acidity).size()).isEqualTo(expectedWines.size());
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
    }

    @Test
    public void testFindAllWinesFromYears() {

    }

    @Test
    public void testFindAllWinesFromWrongYears() {

    }

    @Test
    public void testDeleteWine() {

    }

    @Test
    public void testDeleteNonExistingWine() {

    }

    @Test
    public void testWineIsValid() {

    }
}
