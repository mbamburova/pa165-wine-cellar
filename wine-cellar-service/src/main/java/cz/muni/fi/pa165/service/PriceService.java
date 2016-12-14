package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

public interface PriceService {

    void createPrice(Price p);
    void updatePrice(Price p);
    void deletePrice(Price p);

    Price findPriceById(Long id);
    List<Price> findAllPrices();
    List<Price> findPricesByCurrency(Currency currency);
    List<Price> findPricesByPriceAttribute(BigDecimal price);
    List<Price> findPricesByMarketingEvent(MarketingEvent event);
    List<Price> findPricesByPacking(Packing packing);
}
