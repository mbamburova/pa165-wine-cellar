package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.WineDao;
import cz.muni.fi.pa165.entity.Wine;
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

import java.math.BigDecimal;
import java.time.Year;

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
            .description("Víno zlatavé barvy s ovocnou vůní citrusových plodů a muškátového oříšku." +
                " V chuti nabízí ovocné tóny grapefruitu a zralého citrónu. Ovocnou chuť provází příjemná kyselinka," +
                " díky níž je víno pikantní se suchým závěrem.")
            .notes("20,2°ČNM")
            .alcoholVolume(new BigDecimal(12))
            .residualSugar(new BigDecimal(0.7))
            .acidity(new BigDecimal(6.1))
            .grapeSugarContent(new BigDecimal(0));
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
            .alcoholVolume(new BigDecimal(12))
            .residualSugar(new BigDecimal(6.2))
            .acidity(new BigDecimal(4.6))
            .grapeSugarContent(new BigDecimal(0));
    }

    @BeforeClass
    public void setUpMock(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        Wine veltlinskeZelene = veltlinskeZelene().build();
        Wine muskatMoravsky = muskatMoravsky().build();
        Wine svatovavrinecke = svatovavrinecke().build();

        wineService.create(veltlinskeZelene);
        wineService.create(muskatMoravsky);
        wineService.create(svatovavrinecke);
    }

    @After
    public void tearDown(){

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
    public void testFindAllWines(){

    }

    @Test
    public void testFindWineByName() {

    }

    @Test
    public void testFindWineByVintage() {

    }

    @Test
    public void testFindWineByBatch() {

    }

    @Test
    public void testFindWineByPredicate() {

    }

    @Test
    public void testFindWineByPredicateEquivalent() {

    }

    @Test
    public void testFindWineByDescription() {

    }

    @Test
    public void testFindWineByNotes() {

    }

    @Test
    public void testFindWineByAlcoholVolume() {

    }

    @Test
    public void testFindWineByResidualSugar() {

    }

    @Test
    public void testFindWineByAcidity() {

    }

    @Test
    public void testFindWineByGrapeSugarContent() {

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
