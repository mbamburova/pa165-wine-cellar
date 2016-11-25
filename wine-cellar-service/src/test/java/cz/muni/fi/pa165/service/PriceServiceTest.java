package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.WineBuilder;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.PackingDao;
import cz.muni.fi.pa165.dao.PriceDao;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.PriceDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.entity.Wine;
import org.joda.time.LocalDateTime;
import org.junit.After;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Tomas Gordian on 11/25/2016.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PriceServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private PriceDao priceDao;

    @Mock
    private PackingDto packingDao;

    @Mock
    private WineDto wineDao;

    @Autowired
    @InjectMocks
    private PriceService priceService;

    @InjectMocks
    private PackingService packingService;

    @Autowired
    private WineService wineService;

    private Price price1;
    private Price price2;
    private Price price3;

    private Packing packing1;
    private Packing packing2;

    private Wine veltlinskeZelene;
    private Wine muskatMoravsky;

    private MarketingEvent marketingEvent1;
    private MarketingEvent marketingEvent2;

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

    @BeforeClass
    public void setUpMock(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {

        marketingEvent1 = new MarketingEvent();
        marketingEvent2 = new MarketingEvent();
        marketingEvent1.setDescription("Christmas");
        marketingEvent2.setDescription("New Year");

        muskatMoravsky = muskatMoravsky().build();
        veltlinskeZelene = veltlinskeZelene().build();
        wineService.createWine(muskatMoravsky);
        wineService.createWine(veltlinskeZelene);

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

        price1 = new Price();
        price1.setPacking(packing1);
        price1.setCurrency(Currency.getInstance("CZK"));
        price1.setPrice(new BigDecimal("120"));
        price1.setMarketingEvent(marketingEvent2);
        priceService.createPrice(price1);

        price2 = new Price();
        price2.setPacking(packing2);
        price2.setCurrency(Currency.getInstance("SK"));
        price2.setPrice(new BigDecimal("80"));
        price2.setMarketingEvent(marketingEvent2);
        priceService.createPrice(price2);

        price3 = new Price();
        price3.setPacking(packing2);
        price3.setCurrency(Currency.getInstance("SK"));
        price3.setPrice(new BigDecimal("120"));
        price3.setMarketingEvent(marketingEvent1);
        priceService.createPrice(price3);
    }

    @After
    public void tearDown(){
    }

    @Test
    public void createPrice() {
        Price price = new Price();
        price.setPacking(packing2);
        price.setCurrency(Currency.getInstance("CZK"));
        price.setPrice(new BigDecimal("120"));
        price.setMarketingEvent(marketingEvent1);

        priceService.createPrice(price);
        verify(priceDao).createPrice(price);
    }

    @Test
    public void updatePrice() {
        priceService.updatePrice(price1);
        verify(priceDao).updatePrice(price1);
    }

    @Test
    public void deletePrice(){
        priceService.deletePrice(price2);
        verify(priceDao).deletePrice(price2);
    }

    @Test
    public void findPriceById(){
        when(priceDao.findPriceById(price1.getId()))
                .thenReturn(price1);
        assertThat(priceService.findPriceById(price1.getId()))
                .isEqualToComparingFieldByField(price1);
        verify(priceDao).findPriceById(price1.getId());
    }

    @Test
    public void findAllPrices(){
        List<Price> expected = new ArrayList<>();

        expected.add(price1);
        expected.add(price2);

        when(priceDao.findAllPrices()).thenReturn(expected);
        List<Price> actual = priceService.findAllPrices();

        assertThat(actual.size()).isEqualTo(expected.size());

        verify(priceDao).findAllPrices();
    }

    @Test
    public void findPricesByCurrency(){
        Currency currency = Currency.getInstance("SK");

        List<Price> expected = new ArrayList<>();
        expected.add(price2);
        expected.add(price3);

        when(priceDao.findPricesByCurrency(currency)).thenReturn(expected);
        assertThat(priceDao.findPricesByCurrency(currency).size()).isEqualTo(expected.size());

        verify(priceDao).findPricesByCurrency(currency);
    }

    @Test
    public void findPriceByPriceAttribute(){
        BigDecimal price = new BigDecimal("120");

        List<Price> expected = new ArrayList<>();
        expected.add(price1);
        expected.add(price3);

        when(priceDao.findPricesByPriceAttribute(price)).thenReturn(expected);
        assertThat(priceDao.findPricesByPriceAttribute(price).size()).isEqualTo(expected.size());

        verify(priceDao).findPricesByPriceAttribute(price);
    }

    @Test
    public void findPricesByMarketingEvent(){

        List<Price> expected = new ArrayList<>();
        expected.add(price1);
        expected.add(price2);

        when(priceDao.findPricesByMarketingEvent(marketingEvent2)).thenReturn(expected);
        assertThat(priceDao.findPricesByMarketingEvent(marketingEvent2).size()).isEqualTo(expected.size());

        verify(priceDao).findPricesByMarketingEvent(marketingEvent2);
    }

    @Test
    public void findPricesByPacking(){

        List<Price> expected = new ArrayList<>();
        expected.add(price2);
        expected.add(price3);

        when(priceDao.findPricesByPacking(packing2)).thenReturn(expected);
        assertThat(priceDao.findPricesByPacking(packing2).size()).isEqualTo(expected.size());

        verify(priceDao).findPricesByPacking(packing2);
    }
}
