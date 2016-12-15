package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.WineBuilder;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.dto.WineListCreateDto;
import cz.muni.fi.pa165.dto.WineListDto;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.entity.WineList;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.MarketingEventServiceImpl;
import cz.muni.fi.pa165.service.WineListService;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class WineListFacadeTest extends AbstractTestNGSpringContextTests {

    private WineList wineList1;
    private WineList wineList2;
    private WineListDto wineListDto1;
    private WineListDto wineListDto2;
    private WineListCreateDto wineListCreateDto1;
    private WineListCreateDto wineListCreateDto2;
    private Wine veltlinskeZelene;
    private Wine muskatMoravsky;
    private WineDto veltlinskeZeleneDto;
    private WineDto muskatMoravskyDto;
    private MarketingEvent marketingEvent1;
    private MarketingEventDto marketingEventDto1;

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


    @Mock
    private WineListService wineListService;

    @Mock
    private MarketingEventServiceImpl marketingEventService;

    @InjectMocks
    private WineListFacadeImpl wineListFacade;

    @Captor
    private ArgumentCaptor<WineList> wineListArgumentCaptor;

    @Spy
    @Inject
    private BeanMappingService beanMappingService;

    @BeforeClass
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp(){

        veltlinskeZelene = veltlinskeZelene().build();
        veltlinskeZelene.setId(1L);
        veltlinskeZeleneDto = beanMappingService.mapTo(veltlinskeZelene, WineDto.class);

        muskatMoravsky = muskatMoravsky().build();
        muskatMoravsky.setId(2L);
        muskatMoravskyDto = beanMappingService.mapTo(muskatMoravsky, WineDto.class);

        marketingEvent1 = new MarketingEvent();
        marketingEvent1.setId(1L);
        marketingEvent1.setDescription("marketing event 1");
        marketingEventDto1 = beanMappingService.mapTo(marketingEvent1, MarketingEventDto.class);

        wineList1 = new WineList();
        wineList1.setName("wine list 1");
        wineList1.setWines(Arrays.asList(veltlinskeZelene, muskatMoravsky));
        wineList1.setDate(LocalDateTime.of(2016,11,25,0,0));
        wineList1.setMarketingEvent(marketingEvent1);

        wineListCreateDto1 = new WineListCreateDto();
        wineListCreateDto1.setDate(wineList1.getDate());
        wineListCreateDto1.setMarketingEventId(wineList1.getMarketingEvent().getId());
        wineListCreateDto1.setWinesIds(Arrays.asList(veltlinskeZelene.getId(), muskatMoravsky.getId()));

        wineListDto1 = beanMappingService.mapTo(wineList1, WineListDto.class);
        wineListDto1.setMarketingEvent(marketingEventDto1);
        wineListDto1.setWines(Arrays.asList(veltlinskeZeleneDto, muskatMoravskyDto));

        wineList2 = new WineList();
        wineList2.setName("wine list 2");
        wineList2.setWines(Arrays.asList(muskatMoravsky));
        wineList2.setDate(LocalDateTime.of(2016,10,25,0,0));
        wineList2.setMarketingEvent(marketingEvent1);

        wineListCreateDto2 = new WineListCreateDto();
        wineListCreateDto2.setDate(wineList2.getDate());
        wineListCreateDto2.setMarketingEventId(marketingEvent1.getId());
        wineListCreateDto2.setWinesIds(Arrays.asList(muskatMoravsky.getId()));

        wineListDto2 = beanMappingService.mapTo(wineList2, WineListDto.class);
        wineListDto2.setMarketingEvent(marketingEventDto1);
        wineListDto2.setWines(Arrays.asList(muskatMoravskyDto));
    }

    @Test
    public void create() {
        wineListFacade.createWineList(wineListCreateDto1);
        verify(wineListService).createWineList(wineListArgumentCaptor.capture());
    }

    @Test
    public void update() {
        wineListFacade.updateWineList(wineListDto1);
        verify(wineListService).deleteWineList(wineListArgumentCaptor.capture());
    }

    @Test
    public void delete() {
        wineListFacade.deleteWineList(wineListDto1.getId());
        verify(wineListService).deleteWineList(wineListArgumentCaptor.capture());
    }

    @Test
    public void findById() {
        when(wineListService.findWineListById(1L)).thenReturn(wineList1);
        assertThat(wineListFacade.findWineListById(1L)).isEqualTo(wineList1);
    }

    @Test
    public void findAll() {
        when(wineListService.findAllWineLists()).thenReturn(Arrays.asList(wineList1, wineList2));
        assertEquals(wineListFacade.findAllWineLists().size(), 2);
    }

    @Test
    public void findByName(){
        when(wineListService.findWineListByName("wine list 1")).thenReturn(Collections.singletonList(wineList1));
        assertEquals(wineListFacade.findWineListsByName("wine list 1").size(), 1);
    }

    @Test
    public void findByDate(){
        when(wineListService.findWineListByDate(LocalDateTime.of(2016,11,25,0,0))).thenReturn(Collections.singletonList(wineList1));
        assertEquals(wineListFacade.findWineListsByDate(LocalDateTime.of(2016,11,25,0,0)).size(), 1);
    }

    @Test
    public void findByMarketingEvent(){
        when(wineListService.findWineListByMarketingEvent(marketingEvent1)).thenReturn(Collections.singletonList(wineList1));
        assertEquals(wineListFacade.findWineListsByMarketingEvent(marketingEventDto1).size(), 1);
    }

}