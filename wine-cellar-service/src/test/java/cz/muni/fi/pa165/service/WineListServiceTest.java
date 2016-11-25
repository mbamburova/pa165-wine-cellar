package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.WineBuilder;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.WineListDao;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.entity.WineList;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author MarekScholtz
 * @version 2016.11.25
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class WineListServiceTest extends AbstractTestNGSpringContextTests {

    private WineList wineList1;
    private WineList wineList2;
    private Wine veltlinskeZelene;
    private Wine muskatMoravsky;
    private Wine svatovavrinecke;
    
    @Mock
    private WineListDao wineListDao;

    @Autowired
    @InjectMocks
    private WineService wineService;

    @InjectMocks
    private WineListService wineListService;

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
        List<Wine> wines1 = new ArrayList<>();
        wines1.add(veltlinskeZelene().build());
        wines1.add(muskatMoravsky().build());
        wineList1.setDate(LocalDateTime.now());
        wineList1.setName("anniversary");
        wineList1.setWines(wines1);
        List<Wine> wines2 = new ArrayList<>();
        wines1.add(muskatMoravsky().build());
        wines1.add(svatovavrinecke().build());
        wineList2.setDate(LocalDateTime.now());
        wineList2.setName("birthday");
        wineList2.setWines(wines2);
        
    }

    @Test
    public void createWineList() throws Exception {
        wineListService.createWineList(wineList1);
        verify(wineListDao).createWineList(wineList1);
    }

    @Test
    public void updateWineList() throws Exception {
        wineListService.createWineList(wineList1);
        wineList1.setName("birthday");
        wineListService.updateWineList(wineList1);
        verify(wineListDao).updateWineList(wineList1);
    }

    @Test
    public void findWineListById() throws Exception {
        when(wineListDao.findWineListById(wineList1.getId()))
                .thenReturn(wineList1);
        assertThat(wineListService.findWineListById(wineList1.getId()))
                .isEqualToComparingFieldByField(wineList1);
        verify(wineListDao).findWineListById(wineList1.getId());
    }

    @Test
    public void deleteWineList() throws Exception {
        wineListService.createWineList(wineList1);
        wineListService.deleteWineList(wineList1);
        verify(wineListDao).deleteWineList(wineList1);
    }

    @Test
    public void findAllWineLists() throws Exception {
        List<WineList> expectedWineLists = new ArrayList<>();
        expectedWineLists.add(wineList1);
        expectedWineLists.add(wineList2);
        when(wineListDao.findAllWineLists()).thenReturn(expectedWineLists);
        List<WineList> currentWineLists = wineListService.findAllWineLists();
        assertThat(currentWineLists).isEqualTo(expectedWineLists.size());
        for(int i = 0; i < expectedWineLists.size(); i++) {
            assertThat(currentWineLists.get(i)).isEqualToComparingFieldByField(expectedWineLists.get(i));
        }
        verify(wineListDao).findAllWineLists();
    }
}
