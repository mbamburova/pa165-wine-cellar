package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.config.PersistenceApplicationContext;
import cz.muni.fi.pa165.entity.MarketingEvent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

/**
 * @author Silvia Borzová
 *         31/10/2016
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MarketingEventDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private MarketingEventDao marketingEventDao;

    private MarketingEvent marketingEvent1;
    private MarketingEvent marketingEvent2;

    @BeforeMethod
    public void createMarketingEvents() {
        marketingEvent1 = new MarketingEvent();
        marketingEvent2 = new MarketingEvent();

        marketingEvent1.setDescription("Marketing event 1 description");
        marketingEvent2.setDescription("Marketing event 2 description");

        marketingEventDao.createMarketingEvent(marketingEvent1);
        marketingEventDao.createMarketingEvent(marketingEvent2);
    }

    @Test
    public void createMarketingEvent() {
        MarketingEvent marketingEvent = marketingEventDao.findMarketingEventById(marketingEvent1.getId());
        Assert.assertEquals(marketingEvent, marketingEvent1);
    }

    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void createMarketingEventWithNullDescription() {
        MarketingEvent marketingEvent = new MarketingEvent();
        marketingEvent.setDescription(null);
        marketingEventDao.createMarketingEvent(marketingEvent);
    }

    @Test
    public void updateMarketingEvent() {
        MarketingEvent marketingEvent = marketingEventDao.findMarketingEventById(marketingEvent1.getId());
        marketingEvent.setDescription("Update description");
        marketingEventDao.updateMarketingEvent(marketingEvent);
        Assert.assertEquals(marketingEventDao.findMarketingEventById(marketingEvent.getId()), marketingEvent);
    }

    @Test
    public void deleteMarketingEvent() {
        marketingEventDao.deleteMarketingEvent(marketingEvent1);
        marketingEventDao.deleteMarketingEvent(marketingEvent2);
        Assert.assertNull(marketingEventDao.findMarketingEventById(marketingEvent1.getId()));
        Assert.assertNull(marketingEventDao.findMarketingEventById(marketingEvent2.getId()));
    }

    @Test
    public void getAllMarketingEvent() {
        Assert.assertEquals(marketingEventDao.findAllMarketingEvents().size(), 2);
    }

}