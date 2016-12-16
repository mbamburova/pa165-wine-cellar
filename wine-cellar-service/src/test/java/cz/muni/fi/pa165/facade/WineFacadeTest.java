//package cz.muni.fi.pa165.facade;
//
//import cz.muni.fi.pa165.WineBuilder;
//import cz.muni.fi.pa165.config.ServiceConfiguration;
//import cz.muni.fi.pa165.dto.WineCreateDto;
//import cz.muni.fi.pa165.dto.WineDto;
//import cz.muni.fi.pa165.entity.Wine;
//import cz.muni.fi.pa165.service.BeanMappingServiceImpl;
//import cz.muni.fi.pa165.service.WineService;
//import org.mockito.*;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import javax.inject.Inject;
//import java.math.BigDecimal;
//import java.time.Year;
//import java.util.Arrays;
//import java.util.Collections;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.testng.Assert.assertEquals;
//
///**
// * @author Tomas Gordian on 11/25/2016.
// */
//@ContextConfiguration(classes = ServiceConfiguration.class)
//public class WineFacadeTest extends AbstractTestNGSpringContextTests {
//
//    private Wine veltlinskeZelene;
//    private Wine muskatMoravsky;
//    private WineCreateDto veltlinskeZeleneDto;
//    private WineCreateDto muskatMoravskyDto;
//
//    private WineBuilder veltlinskeZelene() {
//        return new WineBuilder()
//                .name("Veltlínske zelené")
//                .vintage(Year.of(2014))
//                .batch("10/14")
//                .predicate("kabinetní víno")
//                .predicateEquivalent("suché")
//                .description("Elegantní, svěží víno s lehkou aromatikou angreštu a zeleného pepře. Chuťový vjem je tvořen pikantní kyselinkou a kořenito-ovocnými tóny.")
//                .notes("20,0°ČNM")
//                .alcoholVolume(new BigDecimal("10.94"))
//                .residualSugar(new BigDecimal("2.8"))
//                .acidity(new BigDecimal("7.5"))
//                .grapeSugarContent(new BigDecimal("0"));
//    }
//
//    private WineBuilder muskatMoravsky() {
//        return new WineBuilder()
//                .name("Muškát moravský")
//                .vintage(Year.of(2015))
//                .batch("1/14")
//                .predicate("kabinetní víno")
//                .predicateEquivalent("suché")
//                .description("Víno zlatavé barvy s ovocnou vůní citrusových plodů a muškátového oříšku. V chuti nabízí ovocné tóny grapefruitu a zralého citrónu. Ovocnou chuť provází příjemná kyselinka, díky níž je víno pikantní se suchým závěrem.")
//                .notes("20,2°ČNM")
//                .alcoholVolume(new BigDecimal("12"))
//                .residualSugar(new BigDecimal("0.7"))
//                .acidity(new BigDecimal("6.1"))
//                .grapeSugarContent(new BigDecimal("0"));
//    }
//
//    @Mock
//    private WineService wineService;
//
//    @InjectMocks
//    private WineFacadeImpl wineFacade;
//
//    @Spy
//    @Inject
//    private BeanMappingServiceImpl beanMappingService;
//
//    @Captor
//    private ArgumentCaptor<Wine> wineArgumentCaptor;
//
//    @BeforeClass
//    public void setUpMock(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @BeforeMethod
//    public void init(){
//        veltlinskeZelene = veltlinskeZelene().build();
//        veltlinskeZeleneDto = beanMappingService.mapTo(veltlinskeZelene, WineDto.class);
//        muskatMoravsky = muskatMoravsky().build();
//        muskatMoravskyDto = beanMappingService.mapTo(muskatMoravsky, WineDto.class);
//    }
//
//    @Test
//    public void create() {
//        wineFacade.createWine(veltlinskeZeleneDto);
//        verify(wineService).createWine(wineArgumentCaptor.capture());
//    }
//
//    @Test
//    public void delete() {
//        wineFacade.deleteWine(veltlinskeZeleneDto.getId());
//        verify(wineService).deleteWine(wineArgumentCaptor.capture());
//    }
//
//    @Test
//    public void update() {
//        wineFacade.updateWine(veltlinskeZeleneDto);
//        verify(wineService).updateWine(wineArgumentCaptor.capture());
//    }
//
//    @Test
//    public void findById() {
//        when(wineService.findWineById(1L)).thenReturn(veltlinskeZelene);
//        assertThat(wineFacade.findWineById(1L)).isEqualToComparingFieldByField(veltlinskeZelene);
//    }
//
//    @Test
//    public void findAll() {
//        when(wineService.findAllWines()).thenReturn(Arrays.asList(veltlinskeZelene, muskatMoravsky));
//        assertEquals(wineFacade.findAllWines().size(), 2);
//    }
//
//    @Test
//    public void findByName(){
//        when(wineService.findWinesByName("Veltlínske zelené")).thenReturn(Collections.singletonList(veltlinskeZelene));
//        assertEquals(wineFacade.findWinesByName("Veltlínske zelené").size(), 1);
//    }
//
//    @Test
//    public void findByVintage(){
//        when(wineService.findWinesByVintage(Year.of(2014))).thenReturn(Collections.singletonList(veltlinskeZelene));
//        assertEquals(wineFacade.findWinesByVintage(Year.of(2014)).size(), 1);
//    }
//
//    @Test
//    public void findByBatch(){
//        when(wineService.findWineByBatch("10/14")).thenReturn(veltlinskeZelene);
//        assertThat(wineFacade.findWineByBatch("10/14")).isEqualToComparingFieldByField(veltlinskeZelene);
//    }
//
//    @Test
//    public void findByPredicate(){
//        when(wineService.findWinesByPredicate("kabinetní víno")).thenReturn(Collections.singletonList(veltlinskeZelene));
//        assertEquals(wineFacade.findWinesByPredicate("kabinetní víno").size(), 1);
//    }
//
//    @Test
//    public void findByPredicateEquivalent(){
//        when(wineService.findWinesByPredicateEquivalent("suché")).thenReturn(Arrays.asList(veltlinskeZelene, muskatMoravsky));
//        assertEquals(wineFacade.findWinesByPredicateEquivalent("suché").size(), 2);
//    }
//
//    @Test
//    public void findByAlcoholVolume(){
//        when(wineService.findWinesByAlcoholVolume(new BigDecimal("10"), new BigDecimal("11"))).thenReturn(Collections.singletonList(veltlinskeZelene));
//        assertEquals(wineFacade.findWinesByAlcoholVolume(new BigDecimal("10"), new BigDecimal("11")).size(), 1);
//    }
//
//    @Test
//    public void findByResidualSugar(){
//        when(wineService.findWinesByResidualSugar(new BigDecimal("2"), new BigDecimal("3"))).thenReturn(Collections.singletonList(veltlinskeZelene));
//        assertEquals(wineFacade.findWinesByResidualSugar(new BigDecimal("2"), new BigDecimal("3")).size(), 1);
//    }
//
//    @Test
//    public void findByAcidity(){
//        when(wineService.findWinesByAcidity(new BigDecimal("7"), new BigDecimal("8"))).thenReturn(Collections.singletonList(veltlinskeZelene));
//        assertEquals(wineFacade.findWinesByAcidity(new BigDecimal("7"), new BigDecimal("8")).size(), 1);
//    }
//
//    @Test
//    public void findByGrapeSugarContent(){
//        when(wineService.findWinesByGrapeSugarContent(new BigDecimal("0"), new BigDecimal("1"))).thenReturn(Arrays.asList(veltlinskeZelene, muskatMoravsky));
//        assertEquals(wineFacade.findWinesByGrapeSugarContent(new BigDecimal("0"), new BigDecimal("1")).size(), 2);
//    }
//}