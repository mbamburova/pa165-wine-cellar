package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.config.PersistenceApplicationContext;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.entity.WineList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Silvia Borzová
 *         31/10/2016
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class WineListDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public WineListDao wineListDao;

    @Autowired
    public WineDao wineDao;

    @Autowired
    public MarketingEventDao marketingEventDao;

    private WineList wineList1;
    private WineList wineList2;
    private MarketingEvent marketingEvent;
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
                .description("Víno zlatavé barvy s ovocnou vůní citrusových plodů a muškátového oříšku. V chuti nabízí ovocné tóny grapefruitu a zralého citrónu. Ovocnou chuť provází příjemná kyselinka, díky níž je víno pikantní se suchým závěrem.")
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
                .description("Elegantní, svěží víno s lehkou aromatikou angreštu a zeleného pepře. Chuťový vjem je tvořen pikantní kyselinkou a kořenito-ovocnými tóny.")
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
                .description("Jiskrné víno rubínových odstínů barvy. Kořenitá vůně višní a třešňové kůry. Zabalená v nádechu kouře z dubového dřeva. Chuť charakterní pevná, v níž se snoubí tóny višní, svěží kyselinky a příjemného třísla.")
                .notes("30,2°ČNM")
                .alcoholVolume(new BigDecimal("12"))
                .residualSugar(new BigDecimal("6.2"))
                .acidity(new BigDecimal("4.6"))
                .grapeSugarContent(new BigDecimal("0"));
    }

    @BeforeMethod
    public void createWineLists() {
        wineList1 = new WineList();
        wineList2 = new WineList();

        muskatMoravsky = muskatMoravsky().build();
        veltlinskeZelene = veltlinskeZelene().build();
        svatovavrinecke = svatovavrinecke().build();

        wineDao.createWine(muskatMoravsky);
        wineDao.createWine(veltlinskeZelene);
        wineDao.createWine(svatovavrinecke);

        marketingEvent = new MarketingEvent();
        marketingEvent.setDescription("Marketing event");
        marketingEventDao.createMarketingEvent(marketingEvent);


        wineList1.setDate(LocalDateTime.of(2016,11,10,0,0));
        wineList1.setName("Wine List 1");
        wineList1.setMarketingEvent(marketingEvent);
        wineList2.setDate(LocalDateTime.of(2016,12,1,0,0));
        wineList2.setName("wine List 2");
        wineList2.setMarketingEvent(null);

        List<Wine> wines1 = new ArrayList<>();
        wines1.add(muskatMoravsky);
        wines1.add(veltlinskeZelene);
        wineList1.setWines(wines1);

        List<Wine> wines2 = new ArrayList<>();
        wines2.add(svatovavrinecke);
        wineList2.setWines(wines2);

        wineListDao.createWineList(wineList1);
        wineListDao.createWineList(wineList2);
    }

    @Test
    public void createWineList(){
        Assert.assertEquals(wineListDao.findWineListById(wineList1.getId()), wineList1);
    }

    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void createWithNullDate(){
        WineList wineList = new WineList();
        wineList.setDate(null);
        wineListDao.createWineList(wineList);
    }

    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void createWithNullName(){
        WineList wineList = new WineList();
        wineList.setName(null);
        wineListDao.createWineList(wineList);
    }

    @Test
    public void updateWineList() {
        WineList wineList = wineListDao.findWineListById(wineList1.getId());
        wineList.setName("Wine List Update");
        wineList.setDate(LocalDateTime.of(2017,12,12,0,0));
        wineListDao.updateWineList(wineList);
        Assert.assertEquals(wineListDao.findWineListById(wineList.getId()), wineList);
    }

    @Test
    public void deleteWineList(){
        wineListDao.deleteWineList(wineList1);
        Assert.assertEquals(wineListDao.findAllWineLists().size(), 1);
    }

    @Test
    public void getAllWineList(){
        Assert.assertEquals(wineListDao.findAllWineLists().size(), 2);
    }

}