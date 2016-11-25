package cz.muni.fi.pa165.facade;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cz.muni.fi.pa165.WineBuilder;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.MarketingEventDao;
import cz.muni.fi.pa165.dao.WineDao;
import cz.muni.fi.pa165.dao.WineListDao;
import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.dto.WineListDto;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.entity.WineList;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.MarketingEventService;
import cz.muni.fi.pa165.service.WineListService;
import cz.muni.fi.pa165.service.WineService;
import org.joda.time.LocalDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class WineListFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private MarketingEventService marketingEventService;

    @InjectMocks
    private WineListService wineListService;

    @InjectMocks
    private WineService wineService;

    @Autowired
    @InjectMocks
    private WineListFacade wineListFacade;

    @Autowired
    @InjectMocks
    private WineFacade wineFacade;

    @Autowired
    private BeanMappingService beanMappingService;

    private Wine veltlinskeZelene;
    private WineList wineList1;
    private WineList wineList2;

    private WineListDto wineListDto1;
    private WineListDto wineListDto2;

    private WineDto wineDto;

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
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        veltlinskeZelene = veltlinskeZelene().build();
        wineDto = new WineDto();
        wineDto.setName(veltlinskeZelene.getName());
        wineDto.setVintage(veltlinskeZelene.getVintage());
        wineDto.setBatch(veltlinskeZelene.getBatch());
        wineDto.setPredicate(veltlinskeZelene.getPredicate());
        wineDto.setPredicateEquivalent(veltlinskeZelene.getPredicateEquivalent());
        wineDto.setDescription(veltlinskeZelene.getDescription());
        wineDto.setNotes(veltlinskeZelene.getNotes());
        wineDto.setAlcoholVolume(veltlinskeZelene.getAlcoholVolume());
        wineDto.setResidualSugar(veltlinskeZelene.getResidualSugar());
        wineDto.setAcidity(veltlinskeZelene.getAcidity());
        wineDto.setGrapeSugarContent(veltlinskeZelene.getGrapeSugarContent());

        wineListDto1 = new WineListDto();
        wineListDto1.setName("Wine list 1");
        wineListDto1.setDate(new LocalDateTime(2016,12,24,0,0));
        wineListDto1.setMarketingEvent(null);
        wineListDto1.addWine(wineDto);

        wineListDto2 = new WineListDto();
        wineListDto2.setName("Wine list 2");
        wineListDto2.setDate(new LocalDateTime(2017,1,1,0,0));
        wineListDto2.setMarketingEvent(null);
        wineListDto2.addWine(wineDto);

        wineList1 = new WineList();
        wineList1.setName(wineListDto1.getName());
        wineList1.setMarketingEvent(null);
        wineList1.addWine(veltlinskeZelene);
        wineList1.setDate(wineListDto1.getDate());

        wineList2 = new WineList();
        wineList2.setName(wineListDto2.getName());
        wineList2.setMarketingEvent(null);
        wineList2.addWine(veltlinskeZelene);
        wineList2.setDate(wineListDto2.getDate());
    }

    @Test
    public void create(){
        wineListFacade.createWineList(wineListDto1);
        verify(wineListService, times(1)).createWineList(wineList1);
    }

    @Test
    public void delete(){
        wineListFacade.deleteWineList(wineListDto1);
        verify(wineListService, times(1)).deleteWineList(wineList1);
    }

    @Test
    public void update(){
        wineListFacade.updateWineList(wineListDto1);
        verify(wineListService, times(1)).updateWineList(wineList1);
    }


    @Test
    public void findAll(){
        List<WineList> expect = new ArrayList<>();
        expect.add(wineList1);
        expect.add(wineList2);

        when(wineListService.findAllWineLists()).thenReturn(expect);
        List<WineListDto> found = wineListFacade.findAllWineLists();

        Collections.sort(expect, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        Collections.sort(found, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        for(int i = 0; i < expect.size(); i++)
        {
            Assert.assertEquals(beanMappingService.mapTo(
                    expect.get(i), MarketingEventDto.class), found.get(i));
        }
    }

    @Test
    public void findById(){
        when(wineListService.findWineListById(any(Long.class))).thenReturn(wineList1);

        WineListDto result = wineListFacade.findWineListById(1L);
        assertEquals(beanMappingService.mapTo(wineList1, WineListDto.class), result);
    }

    @Test
    public void findByName(){
        when(wineListService.findWineListById(any(Long.class))).thenReturn(wineList1);

        WineListDto result = wineListFacade.findWineListById(1L);
        assertEquals(beanMappingService.mapTo(wineList1, WineListDto.class), result);
    }

    @Test
    public void findByDate(){
        List<WineList> expect = new ArrayList<>();
        expect.add(wineList1);
        when(wineListService.findWineListByDate(any(LocalDateTime.class))).thenReturn(expect);

        List<WineListDto> found =  wineListFacade.findWineListsByDate(new LocalDateTime(20016,1,1,0,0));
        assertEquals(beanMappingService.mapTo(wineList1, WineListDto.class), found);
    }

    @Test
    public void findByMarketingEvent(){
        when(wineListService.findWineListByMarketingEvent(any(MarketingEvent.class))).thenReturn(null);

        List<WineListDto> found =  wineListFacade.findWineListsByMarketingEvent(null);
        assertEquals(null, found);
    }

}