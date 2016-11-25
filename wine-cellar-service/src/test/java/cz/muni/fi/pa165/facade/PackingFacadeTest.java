package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.WineBuilder;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dto.PackingCreateDto;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.PackingService;
import cz.muni.fi.pa165.service.WineService;
import org.joda.time.LocalDateTime;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Michaela Bamburová on 08.11.2016
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PackingFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private PackingService packingService;

    @Mock
    private WineService wineService;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private PackingFacade packingFacade;

    private Packing packing1;
    private Packing packing2;
    private PackingCreateDto packingCreateDto1;
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
        veltlinskeZelene.setId(1L);
        muskatMoravsky = muskatMoravsky().build();
        muskatMoravsky.setId(2L);

        packing1 = new Packing(1L);
        packing1.setVolume(new BigDecimal("1"));
        packing1.setValidFrom(new LocalDateTime(2015,2,1,0,0));
        packing1.setValidTo(new LocalDateTime(2016,2,1,0,0));
        packing1.setWine(veltlinskeZelene);

        packing2 = new Packing(2L);
        packing2.setVolume(new BigDecimal("2"));
        packing2.setValidFrom(new LocalDateTime(2014,2,1,0,0));
        packing2.setValidTo(new LocalDateTime(2017,2,1,0,0));
        packing2.setWine(muskatMoravsky);
    }

    @Test
    public void testCreatePacking() {
//        PackingCreateDto packingCreateDto = new PackingCreateDto();
//        packingCreateDto.setWineId(veltlinskeZelene.getId());
//        packingCreateDto.setValidFrom(packing1.getValidFrom());
//        packingCreateDto.setValidTo(packing1.getValidTo());
//        packingCreateDto.setVolume(packing1.getVolume());
    }

    @Test
    public void testUpdatePacking() {

    }

    @Test
    public void testDeletePacking() {
        PackingDto packingDto = (PackingDto) beanMappingService.mapToDTOWithID(packing2); //???
        packingFacade.deletePacking(packingDto);
        verify(packingService).deletePacking(packing2);
    }

    @Test
    public void testFindPackingById() {
        when(packingService.findPackingById(packing1.getId())).thenReturn(packing1);
        PackingDto packingDto = packingFacade.findPackingById(packing1.getId());

        assertThat(packingFacade.findPackingById(packing1.getId())).isEqualToComparingFieldByField(packingDto);
    }

    @Test
    public void testFindAllPackings() {
        List<Packing> allPackings = new ArrayList<>();
        allPackings.add(packing1);
        allPackings.add(packing2);

        when(packingService.findAllPackings()).thenReturn(allPackings);
        assertThat(packingFacade.findAllPackings().size()).isEqualTo(allPackings.size());
    }

    @Test
    public void testFindPackingsByVolume() {
        List<Packing> expectedPackings = new ArrayList<>();
        expectedPackings.add(packing2);

        when(packingService.findPackingsByVolume(muskatMoravsky.getAlcoholVolume())).thenReturn(expectedPackings);
        assertThat(packingFacade.findPackingByVolume(muskatMoravsky.getAlcoholVolume()).size()).isEqualTo(expectedPackings.size());
    }

    @Test
    public void testFindPackingsByValidFrom() {
        when(packingService.)
    }

    @Test
    public void testFindPackingsByValidTo() {

    }

    @Test
    public void testFindPackingsByWine() {
        List<Packing> expectedPacking = new ArrayList<>();
        expectedPacking.add(packing1);

        /*
        when(packingService.findPackingsByWine(veltlinskeZelene)).thenReturn(expectedPacking);
        assertThat(packingFacade.findPackingByWine(veltlinskeZelene).size()).isEqualTo(expectedPacking.size());
        */
    }

    @Test
    public void testFindPackingsValidForDate() {

    }
}
