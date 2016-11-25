package cz.muni.fi.pa165.facade;

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
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    @BeforeClass
    public void setUpMock(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createMarketingEvent(){
        MarketingEventDto dto = new MarketingEventDto();
        dto.setDescription("Marketing event");

        MarketingEvent event = beanMappingService.mapTo(dto, MarketingEvent.class);

        marketingEventFacade.createMarketingEvent(dto);
        verify(marketingEventService, times(1)).createMarketingEvent(event);
    }

    @Test
    public void deleteMarketingEvent(){

    }

    @Test
    public void updateMarketingEvent(){

    }


    @Test
    public void findAllMarketingEvents(){

    }

    @Test
    public void findMarketingEventById(){

    }

    @Test
    public void findMarketingEventByDescription(){

    }
}