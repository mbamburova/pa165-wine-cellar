package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.WineBuilder;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dto.marketingEvent.MarketingEventDto;
import cz.muni.fi.pa165.dto.packing.PackingDto;
import cz.muni.fi.pa165.dto.price.PriceCreateDto;
import cz.muni.fi.pa165.dto.price.PriceDto;
import cz.muni.fi.pa165.dto.wine.WineDto;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.*;
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
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * @author MarekScholtz
 * @version 2016.11.25
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PriceFacadeTest extends AbstractTestNGSpringContextTests {

    private Price price1;
    private Price price2;
    private PriceDto priceDto1;
    private PriceDto priceDto2;
    private Wine veltlinskeZelene;
    private WineDto veltlinskeZeleneDto;
    private PriceCreateDto priceCreateDto1;
    private PriceCreateDto priceCreateDto2;
    private Packing packing1;
    private Packing packing2;
    private PackingDto packingDto1;
    private PackingDto packingDto2;
    private MarketingEvent marketingEvent1;
    private MarketingEventDto marketingEventDto1;


    @Mock
    private PriceServiceImpl priceService;

    @Mock
    private MarketingEventServiceImpl marketingEventService;

    @Mock
    private PackingServiceImpl packingService;

    @Mock
    private WineServiceImpl wineService;

    @InjectMocks
    private PriceFacadeImpl priceFacade;

    @Captor
    private ArgumentCaptor<Price> priceArgumentCaptor;

    @Spy
    @Inject
    private BeanMappingService beanMappingService;

    private WineBuilder veltlinskeZelene() {
        return new WineBuilder().name("Veltlínske zelené").vintage(Year.of(2014)).batch("10/14").predicate("kabinetní víno").predicateEquivalent("suché").description("Elegantní, svěží víno s lehkou aromatikou angreštu a zeleného pepře." + " Chuťový vjem je tvořen pikantní kyselinkou a kořenito-ovocnými tóny.").notes("20,0°ČNM").alcoholVolume(new BigDecimal("10.94")).residualSugar(new BigDecimal("2.8")).acidity(new BigDecimal("7.5")).grapeSugarContent(new BigDecimal("0"));
    }

    @BeforeClass
    public void setUpMock() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {

        veltlinskeZelene = veltlinskeZelene().build();
        veltlinskeZeleneDto = beanMappingService.mapTo(veltlinskeZelene, WineDto.class);

        packing1 = new Packing();
        packing1.setId(1L);
        packing1.setVolume(new BigDecimal("0.7"));
        packing1.setValidFrom(LocalDateTime.of(2014, 2, 1, 0, 0));
        packing1.setValidTo(LocalDateTime.of(2015, 2, 1, 0, 0));
        packing1.setWine(veltlinskeZelene);
        packingDto1 = beanMappingService.mapTo(packing1, PackingDto.class);
        packingDto1.setWine(veltlinskeZeleneDto);

        marketingEvent1 = new MarketingEvent();
        marketingEvent1.setId(1L);
        marketingEvent1.setDescription("marketing event 1");
        marketingEventDto1 = beanMappingService.mapTo(marketingEvent1, MarketingEventDto.class);

        price1 = new Price();
        price1.setId(1L);
        price1.setPrice(new BigDecimal("100"));
        price1.setCurrency(Currency.getInstance("CZK"));
        price1.setPacking(packing1);
        price1.setMarketingEvent(marketingEvent1);

        priceDto1 = new PriceDto();
        priceDto1.setId(1L);
        priceDto1.setPrice(price1.getPrice());
        priceDto1.setCurrency(price1.getCurrency());
        priceDto1.setPacking(beanMappingService.mapTo(price1.getPacking(), PackingDto.class));
        priceDto1.setMarketingEvent(marketingEventDto1);

        priceCreateDto1 = new PriceCreateDto();
        priceCreateDto1.setPrice(price1.getPrice());
        priceCreateDto1.setCurrency(price1.getCurrency());
        priceCreateDto1.setPackingId(packing1.getId());
        priceCreateDto1.setMarketingEventId(marketingEvent1.getId());

        packing2 = new Packing();
        packing2.setId(2L);
        packing2.setVolume(new BigDecimal("0.35"));
        packing2.setValidFrom(LocalDateTime.of(2014, 2, 1, 0, 0));
        packing2.setValidTo(LocalDateTime.of(2017, 2, 1, 0, 0));
        packing2.setWine(veltlinskeZelene);
        packingDto2 = beanMappingService.mapTo(packing2, PackingDto.class);
        packingDto2.setWine(veltlinskeZeleneDto);

        price2 = new Price();
        price2.setId(2L);
        price2.setPrice(new BigDecimal("4"));
        price2.setCurrency(Currency.getInstance("EUR"));
        price2.setPacking(packing2);
        price2.setMarketingEvent(marketingEvent1);

        priceDto2 = new PriceDto();
        priceDto2.setId(2L);
        priceDto2.setPrice(price2.getPrice());
        priceDto2.setCurrency(price2.getCurrency());
        priceDto2.setPacking(beanMappingService.mapTo(price2.getPacking(), PackingDto.class));
        priceDto2.setMarketingEvent(marketingEventDto1);

        priceCreateDto2 = new PriceCreateDto();
        priceCreateDto2.setPrice(price2.getPrice());
        priceCreateDto2.setCurrency(price2.getCurrency());
        priceCreateDto2.setPackingId(packing2.getId());
        priceCreateDto2.setMarketingEventId(marketingEvent1.getId());

    }

    @Test
    public void createPrice() {
        priceFacade.createPrice(priceCreateDto1);
        verify(priceService).createPrice(priceArgumentCaptor.capture());
    }

    @Test
    public void updatePrice() {
        priceFacade.updatePrice(priceDto1);
        verify(priceService).deletePrice(priceArgumentCaptor.capture());
    }

    @Test
    public void deletePrice() {
        priceFacade.deletePrice(priceDto1.getId());
        verify(priceService).deletePrice(priceArgumentCaptor.capture());
    }

    @Test
    public void findById() {
        when(priceService.findPriceById(1L)).thenReturn(price1);
        assertThat(priceFacade.findPriceById(1L)).isEqualTo(priceDto1);
    }

    @Test
    public void findAll() {
        when(priceService.findAllPrices()).thenReturn(Arrays.asList(price1, price2));
        assertEquals(priceFacade.findAllPrices().size(), 2);
    }

    @Test
    public void findByMarketingEvent() {

        List<Price> prices = new ArrayList<>();
        prices.add(price1);
        prices.add(price2);
        when(priceService.findPricesByMarketingEvent(marketingEvent1)).thenReturn(prices);
        assertEquals(priceFacade.findPricesByMarketingEvent(marketingEventDto1).size(), 2);
    }

    @Test
    public void findByPacking() {
        when(priceService.findPricesByPacking(packing1)).thenReturn(Collections.singletonList(price1));
        assertEquals(priceFacade.findPricesByPacking(packingDto1).size(), 1);
    }

    @Test
    public void findByCurrency() {
        when(priceService.findPricesByPriceAttribute(new BigDecimal("100"))).thenReturn(Collections.singletonList(price1));
        assertEquals(priceFacade.findPricesByPrice(new BigDecimal("100")).size(), 1);
    }

    @Test
    public void findByPrice() {
        when(priceService.findPricesByPriceAttribute(new BigDecimal("100"))).thenReturn(Collections.singletonList(price1));
        assertEquals(priceFacade.findPricesByPrice(new BigDecimal("100")).size(), 1);
    }

}
