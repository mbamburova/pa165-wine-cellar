package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Price;

/**
 * Created by Tomas on 10/30/2016.
 */
public interface PriceDao {

    void createPacking(Price p);
    void deletePacking(Price p);
}
