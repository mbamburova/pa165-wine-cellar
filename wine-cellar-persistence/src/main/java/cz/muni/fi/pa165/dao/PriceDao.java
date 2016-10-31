package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Price;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

/**
 * @author Tomas Gordian on 10/30/2016.
 */
public interface PriceDao {

    void createPrice(Price price);
    void deletePrice(Price price);
    void updatePrice(Price price);
    Price get(Long id);
    List<Price> getAll();
    List<Price> getByPrice(BigDecimal price);
    List<Price> getByCurrency(Currency currency);
    List<Price> getByMarketingEvent(MarketingEvent marketingEvent);
}
