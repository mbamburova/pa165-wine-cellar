package cz.muni.fi.pa165.service;


import cz.muni.fi.pa165.WineBuilder;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.PackingDao;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Wine;
import org.joda.time.LocalDateTime;
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

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class PackingServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private PackingDao packingDao;

    @Mock
    private WineDto wineDao;

    @Autowired
    @InjectMocks
    private PackingService packingService;

    @Autowired
    @InjectMocks
    private WineService wineService;

    private Packing packing1;
    private Packing packing2;
    private Packing packing3;

    private Wine veltlinskeZelene;
    private Wine muskatMoravsky;

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

    @BeforeClass
    public void setUpMock(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        veltlinskeZelene = veltlinskeZelene().build();
        muskatMoravsky = muskatMoravsky().build();
        veltlinskeZelene.setId(1L);
        muskatMoravsky.setId(2L);

        packing1 = new Packing();
        packing1.setVolume(new BigDecimal("0.75"));
        packing1.setWine(veltlinskeZelene);
        packing1.setValidFrom(new LocalDateTime(2016,10,10,0,0));
        packing1.setValidTo(null);
        packingService.createPacking(packing1);

        packing2 = new Packing();
        packing2.setVolume(new BigDecimal("0.75"));
        packing2.setWine(muskatMoravsky);
        packing2.setValidFrom(new LocalDateTime(2014,8,5,0,0));
        packing2.setValidTo(new LocalDateTime(2015,12,11,0,0));
        packingService.createPacking(packing2);

        packing3 = new Packing();
        packing3.setVolume(new BigDecimal("0.5"));
        packing3.setWine(muskatMoravsky);
        packing3.setValidFrom(new LocalDateTime(2016,10,10,0,0));
        packing3.setValidTo(new LocalDateTime(2017,1,1,0,0));
        packingService.createPacking(packing3);
    }

    @Test
    public void createPacking(){
        Packing packing = new Packing();
        packing.setWine(veltlinskeZelene);
        packing.setVolume(new BigDecimal("0.5"));
        packing.setValidFrom(new LocalDateTime(2010,11,2,0,0));
        packing.setValidTo(new LocalDateTime(2017,5,2,0,0));

        packingService.createPacking(packing);
        verify(packingDao, times(1)).createPacking(packing);
    }

    @Test
    public void findPackingById(){
        when(packingDao.findPackingById(packing1.getId()))
                .thenReturn(packing1);

        assertThat(packingService.findPackingById(packing1.getId()))
                .isEqualToComparingFieldByField(packing1);

        verify(packingDao, times(1)).findPackingById(packing1.getId());
    }


    @Test
    public void updatePacking(){
        packingService.updatePacking(packing1);
        verify(packingDao, times(1)).updatePacking(packing1);
    }

    @Test
    public void deletePacking(){
        packingService.deletePacking(packing1);
        verify(packingDao, times(1)).deletePacking(packing1);
    }

//    @Test
//    public void packingIsValid(){
//        when(packingService.isPackingValid(packing1)).thenReturn(true);
//
//        assertThat(packingService.isPackingValid(packing1)).isTrue();
//
//        when(packingService.isPackingValid(packing2)).thenReturn(false);
//        assertThat(packingService.isPackingValid(packing2)).isFalse();
//
//        when(packingService.isPackingValid(packing3)).thenReturn(true);
//        assertThat(packingService.isPackingValid(packing3)).isTrue();
//    }

    @Test
    public void findAllPackings(){
        List<Packing> expect = new ArrayList<>();
        expect.add(packing1);
        expect.add(packing2);
        expect.add(packing3);

        when(packingDao.findAllPackings()).thenReturn(expect);
        List<Packing> found = packingService.findAllPackings();

        assertThat(found.size()).isEqualTo(expect.size());
        assertThat(found).isEqualTo(expect);
    }

    @Test
    public void findPackingsByVolume(){
        List<Packing> expect = new ArrayList<>();
        expect.add(packing1);
        expect.add(packing2);

        when(packingDao.findPackingsByVolume(new BigDecimal("0.75")))
                .thenReturn(expect);
        List<Packing> found = packingService.findPackingsByVolume(new BigDecimal("0.75"));

        assertThat(found.size()).isEqualTo(expect.size());
        assertThat(found).isEqualTo(expect);
    }

    @Test
    public void findPackingsByWine(){
        List<Packing> expect = new ArrayList<>();
        expect.add(packing2);
        expect.add(packing3);

        when(packingDao.findPackingsByWine(muskatMoravsky)).
                thenReturn(expect);
        List<Packing> found = packingService.findPackingsByWine(muskatMoravsky);

        assertThat(found.size()).isEqualTo(expect.size());
        assertThat(found).isEqualTo(expect);
    }
}