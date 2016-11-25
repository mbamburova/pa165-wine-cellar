package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.WineBuilder;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dto.PackingCreateDto;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.WineService;
import org.joda.time.LocalDateTime;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import java.math.BigDecimal;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Tomas Gordian on 11/25/2016.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class WineFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private WineService wineService;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private WineFacade wineFacade;

    private Wine veltlinskeZelene;
    private Wine muskatMoravsky;

    private WineDto veltlinskeZeleneDto;
    private WineDto muskatMoravskyDto;

    private WineBuilder veltlinskeZelene() {
        return new WineBuilder()
                .name("Veltlínske zelené")
                .vintage(Year.of(2014))
                .batch("10/14")
                .predicate("kabinetní víno")
                .predicateEquivalent("suché")
                .description("Elegantní, sv??í víno s lehkou aromatikou angre?tu a zeleného pep?e. " +
                        "Chu?ový vjem je tvo?en pikantní kyselinkou a ko?enito-ovocnými tóny.")
                .notes("20,0°?NM")
                .alcoholVolume(new BigDecimal("10.94"))
                .residualSugar(new BigDecimal("2.8"))
                .acidity(new BigDecimal("7.5"))
                .grapeSugarContent(new BigDecimal("0"));
    }

    private WineBuilder muskatMoravsky() {
        return new WineBuilder()
                .name("Mu?kát moravský")
                .vintage(Year.of(2015))
                .batch("1/14")
                .predicate("kabinetní víno")
                .predicateEquivalent("suché")
                .description("Víno zlatavé barvy s ovocnou v?ní citrusových plod? a mu?kátového o?í?ku." +
                        " V chuti nabízí ovocné tóny grapefruitu a zralého citrónu. Ovocnou chu? provází p?íjemná kyselinka," +
                        " díky ní? je víno pikantní se suchým záv?rem.")
                .notes("20,2°?NM")
                .alcoholVolume(new BigDecimal("12"))
                .residualSugar(new BigDecimal("0.7"))
                .acidity(new BigDecimal("6.1"))
                .grapeSugarContent(new BigDecimal("0"));
    }

    private WineBuilder svatovavrinecke() {
        return new WineBuilder()
                .name("Svatovav?inecké")
                .vintage(Year.of(2015))
                .batch("6/15")
                .predicate("pozdní sb?r")
                .predicateEquivalent("suché")
                .description("Jiskrné víno rubínových odstín? barvy. Ko?enitá v?n? vi?ní a t?e??ové k?ry. " +
                        "Zabalená v nádechu kou?e z dubového d?eva. Chu? charakterní pevná, v ní? se snoubí tóny vi?ní," +
                        " sv??í kyselinky a p?íjemného t?ísla.")
                .notes("30,2°?NM")
                .alcoholVolume(new BigDecimal("12"))
                .residualSugar(new BigDecimal("6.2"))
                .acidity(new BigDecimal("4.6"))
                .grapeSugarContent(new BigDecimal("0"));
    }

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        veltlinskeZelene = veltlinskeZelene().build();
        veltlinskeZelene.setId(1L);
        muskatMoravsky = muskatMoravsky().build();
        muskatMoravsky.setId(2L);

        veltlinskeZeleneDto = (WineDto) beanMappingService.mapToDTOWithID(veltlinskeZelene);
        muskatMoravskyDto = (WineDto) beanMappingService.mapToDTOWithID(muskatMoravsky);
    }

    @Test
    public void createWine() {

        wineFacade.createWine(veltlinskeZeleneDto);
        verify(wineFacade).createWine(veltlinskeZeleneDto);
    }

    @Test
    public void delete(){
        wineFacade.deleteWine(veltlinskeZeleneDto);
        verify(wineFacade).deleteWine(veltlinskeZeleneDto);
    }

    @Test
    public void update(){
        wineFacade.updateWine(veltlinskeZeleneDto);
        verify(wineFacade).updateWine(veltlinskeZeleneDto);
    }

    @Test
    public void findWineById(){
        when(wineService.findWineById(veltlinskeZelene.getId())).thenReturn(veltlinskeZelene);
        WineDto wineDto = wineFacade.findWineById(veltlinskeZeleneDto.getId());

        assertThat(wineFacade.findWineById(veltlinskeZeleneDto.getId())).isEqualToComparingFieldByField(veltlinskeZeleneDto);
    }

    @Test
    public void findAllWines(){

    }

    @Test
    public void findWinesByName(){

    }

    @Test
    public void findWinesByVintage(){

    }

    @Test
    public void findWineByBatch(){

    }

    @Test
    public void findWinesByPredicate(){

    }

    @Test
    public void findWinesByPredicateEquivalent(){

    }

    @Test
    public void findWinesByAlcoholVolume(){

    }

    @Test
    public void findWinesByResidualSugar(){

    }

    @Test
    public void findWinesByAcidity(){

    }

    @Test
    public void findWinesByGrapeSugarContent(){

    }

    @Test
    public void findWinesBetweenYears(){

    }
}