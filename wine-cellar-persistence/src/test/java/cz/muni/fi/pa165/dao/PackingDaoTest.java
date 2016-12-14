package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.config.PersistenceApplicationContext;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Wine;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Tomas Gordian on 10/31/2016.
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PackingDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private PackingDao packingDao;

    @Inject
    private WineDao wineDao;

    private Packing packing1;
    private Wine muskatMoravsky;
    private Wine veltlinskeZelene;
    private Wine svatovavrinecke;

    private WineBuilder muskatMoravsky() {
        return new WineBuilder()
            .name("Muškát moravský")
            .vintage(Year.of(2015))
            .batch("1/14")
            .predicate("kabinetní víno")
            .predicateEquivalent("suché")
            .description("Víno zlatavé barvy s ovocnou vůní citrusových plodů a muškátového oříšku. " +
                "V chuti nabízí ovocné tóny grapefruitu a zralého citrónu. Ovocnou chuť provází příjemná kyselinka," +
                " díky níž je víno pikantní se suchým závěrem.")
            .notes("20,2°ČNM")
            .alcoholVolume(new BigDecimal("12"))
            .residualSugar(new BigDecimal("0.7"))
            .acidity(new BigDecimal("6.1"))
            .grapeSugarContent(new BigDecimal("0"));
    }

    private WineBuilder veltlinskeZelene() {
        return new WineBuilder()
            .name("Veltlínske zelené")
            .vintage(Year.of(2014))
            .batch("10/14")
            .predicate("kabinetní víno")
            .predicateEquivalent("suché")
            .description("Elegantní, svěží víno s lehkou aromatikou angreštu a zeleného pepře." +
                " Chuťový vjem je tvořen pikantní kyselinkou a kořenito-ovocnými tóny.")
            .notes("20,0°ČNM")
            .alcoholVolume(new BigDecimal("10.94"))
            .residualSugar(new BigDecimal("2.8"))
            .acidity(new BigDecimal("7.5"))
            .grapeSugarContent(new BigDecimal("0"));
    }

    private WineBuilder svatovavrinecke() {
        return new WineBuilder()
            .name("Svatovavřinecké")
            .vintage(Year.of(2015))
            .batch("6/15")
            .predicate("pozdní sběr")
            .predicateEquivalent("suché")
            .description("Jiskrné víno rubínových odstínů barvy. Kořenitá vůně višní a třešňové kůry." +
                " Zabalená v nádechu kouře z dubového dřeva. Chuť charakterní pevná, v níž se snoubí tóny višní, svěží kyselinky a příjemného třísla.")
            .notes("30,2°ČNM")
            .alcoholVolume(new BigDecimal("12"))
            .residualSugar(new BigDecimal("6.2"))
            .acidity(new BigDecimal("4.6"))
            .grapeSugarContent(new BigDecimal("0"));
    }

    @BeforeMethod
    public void setup() {

        muskatMoravsky = muskatMoravsky().build();
        veltlinskeZelene = veltlinskeZelene().build();
        svatovavrinecke = svatovavrinecke().build();

        wineDao.createWine(muskatMoravsky);
        wineDao.createWine(veltlinskeZelene);
        wineDao.createWine(svatovavrinecke);

        packing1 = new Packing();
        packing1.setValidFrom(LocalDateTime.of(2015,2,1,0,0));
        packing1.setValidTo(LocalDateTime.of(2016,2,1,0,0));
        packing1.setVolume(new BigDecimal("1"));
        packing1.setWine(muskatMoravsky);

        packingDao.createPacking(packing1);
    }

    @Test
    public void testCreate() {
        Packing packing = packingDao.findPackingById(packing1.getId());
        assertThat(packing).isEqualTo(packing1);
    }

    @Test
    public void update() {
        Packing packing = packingDao.findPackingById(packing1.getId());
        packing.setVolume(new BigDecimal("2"));
        packingDao.updatePacking(packing);

        assertThat(packingDao.findPackingById(packing.getId())).isEqualTo(packing);
    }

    @Test
    public void updateWithNullValidTo() {
        Packing packing = packingDao.findPackingById(packing1.getId());
        packing.setValidTo(null);
        packingDao.updatePacking(packing);

        assertThat(packingDao.findPackingById(packing.getId())).isEqualToComparingFieldByField(packing);
    }

    @Test//(expectedExceptions = java.lang.AssertionError.class)
    public void updateWithNullValidFrom() {
        Packing packing = packingDao.findPackingById(packing1.getId());
        packing.setValidFrom(null);
        packingDao.updatePacking(packing);

        assertThat(packingDao.findPackingById(packing.getId()).getValidFrom()).isEqualTo(packing.getValidFrom());

    }

    @Test
    public void delete() {
        assertThat(packingDao.findPackingById(packing1.getId())).isNotNull();
        packingDao.deletePacking(packing1);
        assertThat(packingDao.findPackingById(packing1.getId())).isNull();
    }


}