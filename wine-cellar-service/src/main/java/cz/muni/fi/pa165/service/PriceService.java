package cz.muni.fi.pa165.service;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import org.springframework.stereotype.Service;

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
    List<Price> findPriceByCurrency(Currency currency);
    List<Price> findPriceByPriceAttribute(BigDecimal price);
    List<Price> findPriceByMarketingEvent(MarketingEvent event);
    List<Price> findPriceByPacking(Packing packing);
}
