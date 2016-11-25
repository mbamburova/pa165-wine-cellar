package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.WineBuilder;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.PriceDao;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.BeanMappingService;
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
import java.util.Currency;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author MarekScholtz
 * @version 2016.11.25
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PriceFacadeTest extends AbstractTestNGSpringContextTests {

    private Price price1;
    private PriceDto price1Dto;
    private PriceCreateDto price1CreateDto;
    private Price price2;
    private PriceDto price2Dto;
    private PriceCreateDto price2CreateDto;
    private PackingDto packingDto;
    private PackingCreateDto packingCreateDto;

    @Mock
    private PriceDao priceDao;

    @Mock
    private BeanMappingService beanMappingService;

    @Autowired
    @InjectMocks
    private PriceFacade priceFacade;

    @Autowired
    @InjectMocks
    private PackingFacade packingFacade;

    @Autowired
    @InjectMocks
    private WineFacade wineFacade;

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
    
    @BeforeClass
    public void setUpMock() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        Wine veltlinskeZelene = veltlinskeZelene().build();
        wineFacade.createWine(beanMappingService.mapTo(veltlinskeZelene, WineDto.class));
        Packing packing = new Packing();
        packing.setValidFrom(new LocalDateTime(2016,1,1,0,0));
        packing.setValidTo(new LocalDateTime(2017,12,31,0,0));
        packing.setVolume(new BigDecimal(0.7));
        packing.setWine(veltlinskeZelene);
        packingCreateDto = beanMappingService.mapTo(packing, PackingCreateDto.class);
        packingDto = beanMappingService.mapTo(packing, PackingDto.class);
        packingFacade.createPacking(packingCreateDto);
        price1.setCurrency(Currency.getInstance("CZK"));
        price2.setCurrency(Currency.getInstance("EUR"));
        price1.setPrice(new BigDecimal(120));
        price2.setPrice(new BigDecimal(4));
        price1.setPacking(packing);
        price2.setPacking(packing);
        price1CreateDto = beanMappingService.mapTo(price1, PriceCreateDto.class);
        price2CreateDto = beanMappingService.mapTo(price2, PriceCreateDto.class);
        price1Dto = beanMappingService.mapTo(price1, PriceDto.class);
        price2Dto = beanMappingService.mapTo(price2, PriceDto.class);
    }

    @Test
    public void createPrice() throws Exception {
        priceFacade.createPrice(price1CreateDto);
        verify(priceFacade).createPrice(price1CreateDto);
    }

    @Test
    public void updatePrice() throws Exception {
        priceFacade.createPrice(price1CreateDto);
        price1.setPrice(new BigDecimal(110));
        price1Dto = beanMappingService.mapTo(price1, PriceDto.class);
        priceFacade.updatePrice(price1Dto);
        verify(priceFacade).updatePrice(price1Dto);
    }

    @Test
    public void findPriceById() throws Exception {
        when(priceFacade.findPriceById(price1.getId())).thenReturn(price1Dto);
        assertThat(priceFacade.findPriceById(price1.getId())).isEqualToComparingFieldByField(price1);
        verify(priceFacade).findPriceById(price1.getId());
    }

    @Test
    public void deletePrice() throws Exception {
        priceFacade.createPrice(price1CreateDto);
        price1Dto = beanMappingService.mapTo(price1CreateDto, PriceDto.class);
        priceFacade.deletePrice(price1Dto);
        verify(priceFacade).deletePrice(price1Dto);
    }

    @Test
    public void findAllPrices() throws Exception {
        List<Price> expectedPrices = new ArrayList<>();
        expectedPrices.add(price1);
        expectedPrices.add(price2);
        when(priceDao.findAllPrices()).thenReturn(expectedPrices);
        List<PriceDto> currentPrices = priceFacade.findAllPrices();
        assertThat(currentPrices).isEqualTo(expectedPrices.size());
        for(int i = 0; i < expectedPrices.size(); i++) {
            assertThat(currentPrices.get(i)).isEqualToComparingFieldByField(expectedPrices.get(i));
        }
        verify(priceFacade).findAllPrices();
    }
}
