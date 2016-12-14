package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.MarketingEventDao;
import cz.muni.fi.pa165.entity.MarketingEvent;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author MarekScholtz
 * @version 2016.11.25
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class MarketingEventServiceTest extends AbstractTestNGSpringContextTests {

    private MarketingEvent marketingEvent1;
    private MarketingEvent marketingEvent2;

    @Mock
    private MarketingEventDao marketingEventDao;

    @Autowired
    @InjectMocks
    private MarketingEventService marketingEventService;

    @BeforeClass
    public void setUpMock() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        marketingEvent1 = new MarketingEvent();
        marketingEvent1.setDescription("anniversary");
        marketingEvent2 = new MarketingEvent();
        marketingEvent2.setDescription("birthday");
    }

    @Test
    public void createMarketingEvent() {
        marketingEventService.createMarketingEvent(marketingEvent1);
        verify(marketingEventDao).createMarketingEvent(marketingEvent1);
    }

    @Test
    public void updateMarketingEvent() {
        marketingEventService.createMarketingEvent(marketingEvent1);
        marketingEvent1.setDescription("birthday");
        marketingEventService.updateMarketingEvent(marketingEvent1);
        verify(marketingEventDao).updateMarketingEvent(marketingEvent1);
    }

    @Test
    public void findMarketingEventById() {
        when(marketingEventDao.findMarketingEventById(marketingEvent1.getId()))
                .thenReturn(marketingEvent1);
        assertThat(marketingEventService.findMarketingEventById(marketingEvent1.getId()))
                .isEqualToComparingFieldByField(marketingEvent1);
        verify(marketingEventDao).findMarketingEventById(marketingEvent1.getId());
    }

    @Test
    public void deleteMarketingEvent() {
        marketingEventService.createMarketingEvent(marketingEvent1);
        marketingEventService.deleteMarketingEvent(marketingEvent1);
        verify(marketingEventDao).deleteMarketingEvent(marketingEvent1);
    }

    @Test
    public void findAllMarketingEvents() {
        List<MarketingEvent> expectedMarketingEvents = new ArrayList<>();
        expectedMarketingEvents.add(marketingEvent1);
        expectedMarketingEvents.add(marketingEvent2);
        when(marketingEventDao.findAllMarketingEvents()).thenReturn(expectedMarketingEvents);
        List<MarketingEvent> currentMarketingEvents = marketingEventService.findAllMarketingEvents();
        for(int i = 0; i < expectedMarketingEvents.size(); i++) {
            assertThat(currentMarketingEvents.get(i)).isEqualToComparingFieldByField(expectedMarketingEvents.get(i));
        }
        verify(marketingEventDao).findAllMarketingEvents();
    }
}
