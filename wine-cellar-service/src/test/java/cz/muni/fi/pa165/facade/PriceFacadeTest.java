package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.WineBuilder;
import cz.muni.fi.pa165.dao.PriceDao;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.PackingService;
import cz.muni.fi.pa165.service.PriceService;
import cz.muni.fi.pa165.service.WineService;
import org.joda.time.LocalDateTime;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PriceFacadeTest {

    private Price priceCZK;
    private Price priceEUR;
    private Packing packing;

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

    @Mock
    private PriceDao priceDao;

    @Autowired
    @InjectMocks
    private PriceService priceService;

    @InjectMocks
    private PackingService packingService;

    @InjectMocks
    private WineService wineService;

    @BeforeClass
    public void setUpMock() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        Wine veltlinskeZelene = veltlinskeZelene().build();
        wineService.createWine(veltlinskeZelene);
        packing.setValidFrom(new LocalDateTime(2016,1,1,0,0));
        packing.setValidTo(new LocalDateTime(2017,12,31,0,0));
        packing.setVolume(new BigDecimal(0.7));
        packing.setWine(veltlinskeZelene);
        packingService.createPacking(packing);
        priceCZK.setCurrency(Currency.getInstance("CZK"));
        priceEUR.setCurrency(Currency.getInstance("EUR"));
        priceCZK.setPrice(new BigDecimal(120));
        priceEUR.setPrice(new BigDecimal(4));
        priceCZK.setPacking(packing);
        priceEUR.setPacking(packing);
        priceService.createPrice(priceCZK);
        priceService.createPrice(priceEUR);
    }

    @Test
    public void createPrice() throws Exception {
        priceService.createPrice(priceCZK);
        verify(priceDao).createPrice(priceCZK);
    }

    @Test
    public void updatePrice() throws Exception {
        priceService.createPrice(priceCZK);
        priceCZK.setPrice(new BigDecimal(110));
        priceService.updatePrice(priceCZK);
        verify(priceDao).updatePrice(priceCZK);
    }

    @Test
    public void findPriceById() throws Exception {
        when(priceDao.findPriceById(priceCZK.getId()))
                .thenReturn(priceCZK);
        assertThat(priceService.findPriceById(priceCZK.getId()))
                .isEqualToComparingFieldByField(priceCZK);
        verify(priceDao).findPriceById(priceCZK.getId());
    }

    @Test
    public void deletePrice() throws Exception {
        priceService.createPrice(priceCZK);
        priceService.deletePrice(priceCZK);
        verify(priceDao).deletePrice(priceCZK);
    }

    @Test
    public void findAllPrices() throws Exception {
        List<Price> expectedPrices = new ArrayList<>();
        expectedPrices.add(priceCZK);
        expectedPrices.add(priceEUR);
        when(priceDao.findAllPrices()).thenReturn(expectedPrices);
        List<Price> currentPrices = priceService.findAllPrices();
        assertThat(currentPrices).isEqualTo(expectedPrices.size());
        for(int i = 0; i < expectedPrices.size(); i++) {
            assertThat(currentPrices.get(i)).isEqualToComparingFieldByField(expectedPrices.get(i));
        }
        verify(priceDao).findAllPrices();
    }
    
}
