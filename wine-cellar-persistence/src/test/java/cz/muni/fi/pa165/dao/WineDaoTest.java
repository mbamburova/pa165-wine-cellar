package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.entity.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;
import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MarekScholtz
 * @version 2016.10.30
 */
//@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class WineDaoTest {

    @Autowired
    private WineDao wineDao;

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

    private WineBuilder svatovavrinecke() {
        return new WineBuilder()
            .name("Svatovavřinecké")
            .vintage(Year.of(2015))
            .batch("6/15")
            .predicate("pozdní sběr")
            .predicateEquivalent("suché")
            .description("Jiskrné víno rubínových odstínů barvy. Kořenitá vůně višní a třešňové kůry. Zabalená v nádechu kouře z dubového dřeva. Chuť charakterní pevná, v níž se snoubí tóny višní, svěží kyselinky a příjemného třísla.")
            .notes("30,2°ČNM")
            .alcoholVolume(new BigDecimal(12))
            .residualSugar(new BigDecimal(6.2))
            .acidity(new BigDecimal(4.6))
            .grapeSugarContent(new BigDecimal(0));
    }


    @Test
    public void create() {
        Wine veltlinskeZelene = veltlinskeZelene().build();
        Wine muskatMoravsky = muskatMoravsky().build();
        wineDao.createWine(veltlinskeZelene);
        wineDao.createWine(muskatMoravsky);
        assertThat(wineDao.getWineById(veltlinskeZelene.getId())).isEqualToComparingFieldByField(veltlinskeZelene);
        assertThat(wineDao.getWineById(muskatMoravsky.getId())).isEqualToComparingFieldByField(muskatMoravsky);
    }

    @Test
    public void update() {
        Wine svatovavrinecke = svatovavrinecke().build();
        wineDao.createWine(svatovavrinecke);
        svatovavrinecke.setVintage(Year.of(2016));
        wineDao.updateWine(svatovavrinecke);
        assertThat(wineDao.getWineById(svatovavrinecke.getId())).isEqualToComparingFieldByField(svatovavrinecke);
    }

    @Test
    public void delete() {
        Wine veltlinskeZelene = veltlinskeZelene().build();
        wineDao.createWine(veltlinskeZelene);
        assertThat(wineDao.getWineById(veltlinskeZelene.getId())).isNotNull();
        wineDao.deleteWine(veltlinskeZelene);
        assertThat(wineDao.getWineById(veltlinskeZelene.getId())).isNull();
    }

    @Test
    public void findAll() {
        List<Wine> found = wineDao.getAllWines();
        Assert.assertEquals(found.size(), 3);
    }

    @Test
    public void findByName() {
        wineDao.createWine(veltlinskeZelene().build());
        wineDao.createWine(muskatMoravsky().build());
        wineDao.createWine(svatovavrinecke().build());
        List<Wine> found = wineDao.findByName("Veltlínske");
        Assert.assertEquals(found.size(), 1);
        Assert.assertEquals(found.get(0).getName(), "Veltlínske zelené");
    }

    @Test
    public void findByVintage() {
        wineDao.createWine(veltlinskeZelene().build());
        wineDao.createWine(muskatMoravsky().build());
        wineDao.createWine(svatovavrinecke().build());
        List<Wine> found = wineDao.findByVintage(Year.of(2015));
        Assert.assertEquals(found.size(), 2);
        Assert.assertEquals(found.get(0).getVintage(), Year.of(2015));
        Assert.assertEquals(found.get(1).getVintage(), Year.of(2015));
    }

    @Test
    public void findByPredicate() {
        wineDao.createWine(veltlinskeZelene().build());
        wineDao.createWine(muskatMoravsky().build());
        wineDao.createWine(svatovavrinecke().build());
        List<Wine> found = wineDao.findByPredicate("kabinetní");
        Assert.assertEquals(found.size(), 2);
        Assert.assertEquals(found.get(0).getPredicate(), "kabinetní víno");
        Assert.assertEquals(found.get(1).getPredicate(), "kabinetní víno");
    }

}
