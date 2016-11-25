package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.MarketingEventDao;
import cz.muni.fi.pa165.entity.MarketingEvent;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;

/**
 * @author MarekScholtz
 * @version 2016.11.25
 */
public class MarketingEventServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private MarketingEventDao marketingEventDao;

    @InjectMocks
    private MarketingEventService marketingEventService;

    @BeforeClass
    public void setUpMock(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createMarketingEvent() throws Exception {
        MarketingEvent marketingEvent = new MarketingEvent();
        marketingEvent.setDescription("anniversary");
        marketingEventService.createMarketingEvent(marketingEvent);
        verify(marketingEventDao).createMarketingEvent(marketingEvent);
    }

    @Test
    public void updateMarketingEvent() throws Exception {
        MarketingEvent marketingEvent = new MarketingEvent();
        marketingEvent.setDescription("anniversary");
        marketingEventService.createMarketingEvent(marketingEvent);
        marketingEvent.setDescription("birthday");
        marketingEventService.updateMarketingEvent(marketingEvent);
        verify(marketingEventDao).updateMarketingEvent(marketingEvent);
    }

    @Test
    public void findMarketingEventById() throws Exception {

    }

    @Test
    public void deleteMarketingEvent() throws Exception {

    }

    @Test
    public void findAllMarketingEvents() throws Exception {

    }
}
