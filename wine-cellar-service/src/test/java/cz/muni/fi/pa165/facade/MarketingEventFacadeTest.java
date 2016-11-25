package cz.muni.fi.pa165.facade;

import java.util.*;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.MarketingEventDao;
import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.MarketingEventService;
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
import org.testng.annotations.BeforeMethod;

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
public class MarketingEventFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private MarketingEventDao marketingEventDao;

    @Autowired
    @InjectMocks
    private MarketingEventService marketingEventService;

    @Autowired
    @InjectMocks
    private MarketingEventFacade marketingEventFacade;

    @Autowired
    private BeanMappingService beanMappingService;

    private MarketingEventDto eventDto1;
    private MarketingEventDto eventDto2;

    private MarketingEvent event1;
    private MarketingEvent event2;

    @BeforeClass
    public void setUpMock(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void init(){
        eventDto1 = new MarketingEventDto();
        eventDto1.setDescription("Marketing event 1");

        eventDto2 = new MarketingEventDto();
        eventDto2.setDescription("Marketing event 2");

        event1 = new MarketingEvent();
        event1.setDescription(eventDto1.getDescription());

        event2 = new MarketingEvent();
        event2.setDescription(eventDto2.getDescription());
    }


    @Test
    public void createMarketingEvent(){
        MarketingEventDto dto = new MarketingEventDto();
        dto.setDescription("Marketing event");

        MarketingEvent event = new MarketingEvent();
        event.setDescription(event.getDescription());

        marketingEventFacade.createMarketingEvent(dto);
        verify(marketingEventService, times(1)).createMarketingEvent(event);
    }

    @Test
    public void deleteMarketingEvent(){
        marketingEventFacade.deleteMarketingEvent(eventDto1);
        verify(marketingEventService, times(1)).deleteMarketingEvent(
                beanMappingService.mapTo(eventDto1, MarketingEvent.class));
    }

    @Test
    public void updateMarketingEvent(){
        marketingEventFacade.updateMarketingEvent(eventDto1);
        verify(marketingEventService, times(1)).updateMarketingEvent(
                beanMappingService.mapTo(eventDto1, MarketingEvent.class)
        );
    }


    @Test
    public void findAllMarketingEvents(){
        List<MarketingEvent> expect = new ArrayList<>();
        expect.add(event1);
        expect.add(event2);

        when(marketingEventService.findAllMarketingEvents()).thenReturn(expect);
        List<MarketingEventDto> found = marketingEventFacade.findAllMarketingEvents();

        Collections.sort(expect, (o1, o2) -> o1.getDescription().compareTo(o2.getDescription()));
        Collections.sort(found, (o1, o2) -> o1.getDescription().compareTo(o2.getDescription()));

        for(int i = 0; i < expect.size(); i++)
        {
            Assert.assertEquals(beanMappingService.mapTo(
                    expect.get(i), MarketingEventDto.class), found.get(i));
        }
    }

    @Test
    public void findMarketingEventById(){
        when(marketingEventService.findMarketingEventById(any(Long.class))).thenReturn(event1);

        MarketingEventDto result = marketingEventFacade.findMarketingEventById(1L);
        assertEquals(beanMappingService.mapTo(event1, MarketingEventDto.class), result);
    }

}