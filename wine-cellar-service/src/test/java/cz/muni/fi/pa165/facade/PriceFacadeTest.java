package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.service.*;
import org.joda.time.LocalDateTime;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
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
    private PriceCreateDto priceCreateDto1;
    private PriceCreateDto priceCreateDto2;
    private Packing packing1;
    private Packing packing2;
    private PackingDto packingDto1;
    private PackingDto packingDto2;
    private PackingCreateDto packingCreateDto1;
    private PackingCreateDto packingCreateDto2;
    private MarketingEvent marketingEvent1;
    private MarketingEvent marketingEvent2;
    private MarketingEventDto marketingEventDto1;
    private MarketingEventDto marketingEventDto2;

    @Mock
    private PriceService priceService;

    @Mock
    private PackingService packingService;

    @Mock
    private WineService wineService;

    @Mock
    private MarketingEventService marketingEventService;
    
    @InjectMocks
    private PriceFacade priceFacade = new PriceFacadeImpl();

    @Captor
    private ArgumentCaptor<Price> priceArgumentCaptor;
    
    @Spy
    @Inject
    private final BeanMappingService beanMappingService = new BeanMappingServiceImpl();
    
    @BeforeClass
    public void setUpMock() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        
        packing1 = new Packing();
        packing1.setId(1L);
        packing1.setVolume(new BigDecimal(0.7));
        packing1.setValidFrom(new LocalDateTime(2014,2,1,0,0));
        packing1.setValidTo(new LocalDateTime(2015,2,1,0,0));
        packingDto1 = beanMappingService.mapTo(packing1, PackingDto.class);
        packingCreateDto1 = beanMappingService.mapTo(packing1, PackingCreateDto.class);
        marketingEvent1 = new MarketingEvent();
        marketingEvent1.setId(1L);
        marketingEvent1.setDescription("marketing event 1");
        marketingEventDto1 = beanMappingService.mapTo(marketingEvent1, MarketingEventDto.class);
        price1 = new Price();
        price1.setPrice(new BigDecimal(100));
        price1.setCurrency(Currency.getInstance("CZK"));
        price1.setPacking(packing1);
        price1.setMarketingEvent(marketingEvent1);
        priceDto1 = new PriceDto();
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
        packing2.setVolume(new BigDecimal(0.35));
        packing2.setValidFrom(new LocalDateTime(2014,2,1,0,0));
        packing2.setValidTo(new LocalDateTime(2017,2,1,0,0));
        packingDto2 = beanMappingService.mapTo(packing2, PackingDto.class);
        packingCreateDto2 = beanMappingService.mapTo(packing2, PackingCreateDto.class);
        marketingEvent2 = new MarketingEvent();
        marketingEvent2.setId(2L);
        marketingEvent2.setDescription("marketing event 2");
        marketingEventDto2 = beanMappingService.mapTo(marketingEvent2, MarketingEventDto.class);
        price2 = new Price();
        price2.setPrice(new BigDecimal(4));
        price2.setCurrency(Currency.getInstance("EUR"));
        price2.setPacking(packing2);
        price2.setMarketingEvent(marketingEvent2);
        priceDto2 = new PriceDto();
        priceDto2.setPrice(price2.getPrice());
        priceDto2.setCurrency(price2.getCurrency());
        priceDto2.setPacking(beanMappingService.mapTo(price2.getPacking(), PackingDto.class));
        priceDto2.setMarketingEvent(marketingEventDto2);
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
        priceFacade.deletePrice(priceDto1);
        verify(priceService).deletePrice(priceArgumentCaptor.capture());
    }

    @Test
    public void findById() {
        when(priceService.findPriceById(1L)).thenReturn(price1);
        assertThat(priceFacade.findPriceById(1L)).isEqualToIgnoringGivenFields(price1, "price", "packing", "marketingEvent");
    }

    @Test
    public void findAll() {
        when(priceService.findAllPrices()).thenReturn(Arrays.asList(price1, price2));
        assertEquals(priceFacade.findAllPrices().size(), 2);
    }

    @Test
    public void findByMarketingEvent() {
        when(priceService.findPriceByMarketingEvent(marketingEvent1)).thenReturn(Collections.singletonList(price1));
        assertEquals(priceFacade.findPricesByMarketingEvent(marketingEventDto1).size(), 1);
    }

    @Test
    public void findByPacking() {
        when(priceService.findPriceByPacking(packing1)).thenReturn(Collections.singletonList(price1));
        assertEquals(priceFacade.findPricesByPacking(packingDto1).size(), 1);
    }

    @Test
    public void findByCurrency() {
        when(priceService.findPriceByPriceAttribute(new BigDecimal(100))).thenReturn(Collections.singletonList(price1));
        assertEquals(priceFacade.findPricesByPrice(new BigDecimal(100)).size(), 1);
    }

    @Test
    public void findByPrice() {
        when(priceService.findPriceByPriceAttribute(new BigDecimal(100))).thenReturn(Collections.singletonList(price1));
        assertEquals(priceFacade.findPricesByPrice(new BigDecimal(100)).size(), 1);
    }

}
