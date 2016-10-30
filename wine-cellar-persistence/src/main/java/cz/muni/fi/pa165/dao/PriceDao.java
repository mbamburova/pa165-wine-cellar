package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Price;

/**
 * @author Tomas Gordian on 10/30/2016.
 */
public interface PriceDao {

    void createPrice(Price price);
    void deletePrice(Price price);
    void updatePrice(Price price);
    void get(Long id);
}
