package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.BeanMappingServiceImpl;
import cz.muni.fi.pa165.service.MarketingEventService;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class MarketingEventFacadeTest extends AbstractTestNGSpringContextTests {

    private MarketingEvent event1;
    private MarketingEvent event2;
    private MarketingEventDto eventDto1;

    @Mock
    private MarketingEventService marketingEventService;

    @InjectMocks
    private MarketingEventFacade marketingEventFacade = new MarketingEventFacadeImpl();

    @Spy
    @Inject
    private final BeanMappingService beanMappingService = new BeanMappingServiceImpl();

    @Captor
    private ArgumentCaptor<MarketingEvent> marketingEventArgumentCaptor;

    @BeforeClass
    public void setUpMock(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void init(){
        event1 = new MarketingEvent();
        event1.setDescription("event 1");
        event2 = new MarketingEvent();
        event2.setDescription("event 2");
        eventDto1 = new MarketingEventDto();
        eventDto1.setDescription(event1.getDescription());
    }


    @Test
    public void create() {
        marketingEventFacade.createMarketingEvent(eventDto1);
        verify(marketingEventService).createMarketingEvent(marketingEventArgumentCaptor.capture());
    }

    @Test
    public void delete() {
        marketingEventFacade.deleteMarketingEvent(eventDto1);
        verify(marketingEventService).deleteMarketingEvent(marketingEventArgumentCaptor.capture());
    }

    @Test
    public void update() {
        marketingEventFacade.updateMarketingEvent(eventDto1);
        verify(marketingEventService).updateMarketingEvent(marketingEventArgumentCaptor.capture());
    }

    @Test
    public void findById() {
        when(marketingEventService.findMarketingEventById(1L)).thenReturn(event1);
        assertThat(marketingEventFacade.findMarketingEventById(1L)).isEqualToComparingFieldByField(event1);
    }

    @Test
    public void findAll() {
        when(marketingEventService.findAllMarketingEvents()).thenReturn(Arrays.asList(event1, event2));
        assertEquals(marketingEventFacade.findAllMarketingEvents().size(), 2);
    }
}