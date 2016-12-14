package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.WineBuilder;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dto.PackingCreateDto;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.PackingService;
import cz.muni.fi.pa165.service.WineService;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
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
 * @author Michaela Bamburová on 08.11.2016
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PackingFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    private Packing packing1;
    private Packing packing2;
    private PackingDto packingDto1;
    private PackingDto packingDto2;
    private PackingCreateDto packingCreateDto1;
    private PackingCreateDto packingCreateDto2;
    private Wine veltlinskeZelene;
    private Wine muskatMoravsky;
    private WineDto veltlinskeZeleneDto;
    private WineDto muskatMoravskyDto;

    @Mock
    private PackingService packingService;

    @Mock
    private WineService wineService;

    @InjectMocks
    private PackingFacadeImpl packingFacade;

    @Captor
    private ArgumentCaptor<Packing> packingArgumentCaptor;

    @Spy
    @Inject
    private BeanMappingService beanMappingService;

    @BeforeClass
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
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
    
    @BeforeMethod
    public void setUp() {

        veltlinskeZelene = veltlinskeZelene().build();
        veltlinskeZelene.setId(1l);
        veltlinskeZeleneDto = beanMappingService.mapTo(veltlinskeZelene, WineDto.class);
        packing1 = new Packing();
        packing1.setVolume(new BigDecimal("0.7"));
        packing1.setValidFrom(LocalDateTime.of(2014,2,1,0,0));
        packing1.setValidTo(LocalDateTime.of(2015,2,1,0,0));
        packing1.setWine(veltlinskeZelene);
        packingDto1 = beanMappingService.mapTo(packing1, PackingDto.class);
        packingCreateDto1 = beanMappingService.mapTo(packing1, PackingCreateDto.class);
        packingCreateDto1.setWineId(veltlinskeZelene.getId());

        muskatMoravsky = muskatMoravsky().build();
        muskatMoravsky.setId(2L);
        muskatMoravskyDto = beanMappingService.mapTo(muskatMoravsky, WineDto.class);
        packing2 = new Packing();
        packing2.setVolume(new BigDecimal("0.35"));
        packing2.setValidFrom(LocalDateTime.of(2014,2,1,0,0));
        packing2.setValidTo(LocalDateTime.of(2017,2,1,0,0));
        packing2.setWine(muskatMoravsky);
        packingDto2 = beanMappingService.mapTo(packing2, PackingDto.class);
        packingCreateDto2 = beanMappingService.mapTo(packing2, PackingCreateDto.class);
        packingCreateDto2.setWineId(muskatMoravsky.getId());
    }

    @Test
    public void create() {
        packingFacade.createPacking(packingCreateDto1);
        verify(packingService).createPacking(packingArgumentCaptor.capture());
    }

    @Test
    public void update() {
        packingFacade.updatePacking(packingDto1);
        verify(packingService).deletePacking(packingArgumentCaptor.capture());
    }

    @Test
    public void delete() {
        packingFacade.deletePacking(packingDto1.getId());
        verify(packingService).deletePacking(packingArgumentCaptor.capture());
    }

    @Test
    public void findById() {
        when(packingService.findPackingById(1L)).thenReturn(packing1);
        assertThat(packingFacade.findPackingById(1L)).isEqualToIgnoringGivenFields(packing1, "validFrom", "validTo", "wine");
    }

    @Test
    public void findAll() {
        when(packingService.findAllPackings()).thenReturn(Arrays.asList(packing1, packing2));
        assertEquals(packingFacade.findAllPackings().size(), 2);
    }

    @Test
    public void findByVolume() {
        when(packingService.findPackingsByVolume(new BigDecimal(0.7))).thenReturn(Collections.singletonList(packing1));
        assertEquals(packingFacade.findPackingsByVolume(new BigDecimal(0.7)).size(), 1);
    }

    @Test
    public void findByWine() {
        when(packingService.findPackingsByWine(veltlinskeZelene)).thenReturn(Collections.singletonList(packing1));
        assertEquals(packingFacade.findPackingsByWine(veltlinskeZeleneDto).size(), 1);
    }
}
