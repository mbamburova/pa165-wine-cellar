package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

/**
 * @author Tomas Gordian on 10/30/2016.
 */
public interface PriceDao {

    /**
     * Creates new price.
     * @param price
     */
    void createPrice(Price price);

    /**
     * Deletes given price.
     * @param price
     */
    void deletePrice(Price price);

    /**
     * Updates given price.
     * @param price
     */
    void updatePrice(Price price);

    /**
     *  get price by given id
     * @param id
     * @return price
     */
    Price findPriceById(Long id);

    /**
     *  get all prices
     * @return list of prices
     */
    List<Price> findAllPrices();

    /**
     *  get prices by given price
     * @param price
     * @return list of prices
     */
    List<Price> findPriceByPrice(BigDecimal price);

    /**
     *  get prices by given currency
     * @param currency
     * @return list of prices
     */
    List<Price> findPriceByCurrency(Currency currency);

    /**
     *  get prices by given marketingEvent
     * @param marketingEvent
     * @return list of prices
     */
    List<Price> findPriceByMarketingEvent(MarketingEvent marketingEvent);

    /**
     *  get prices by given packing
     * @param packing
     * @return list of prices
     */
    List<Price> findPriceByPacking(Packing packing);
}
