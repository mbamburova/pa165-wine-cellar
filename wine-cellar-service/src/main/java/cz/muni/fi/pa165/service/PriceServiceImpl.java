package cz.muni.fi.pa165.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import cz.muni.fi.pa165.dao.PriceDao;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.exception.WineCellarDataAccessException;
import org.dozer.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceDao priceDao;

    @Override
    public void createPrice(Price p) {
        try {
            priceDao.createPrice(p);
        }catch (Exception e){
            throw new WineCellarDataAccessException("Cannot create price", e);
        }
    }

    @Override
    public void updatePrice(Price p) {
        try{
            priceDao.updatePrice(p);
        }catch(Exception e){
            throw new WineCellarDataAccessException("Cannot update price", e);
        }
    }

    @Override
    public void deletePrice(Price p) {
        try{
            priceDao.deletePrice(p);
        }catch(Exception e){
            throw new WineCellarDataAccessException("Cannot delete price", e);
        }
    }

    @Override
    public Price findPriceById(Long id) {
        try{
            return priceDao.findPriceById(id);
        }catch(Exception e){
            throw new WineCellarDataAccessException("Cannot find price by id "
                    + id, e);
        }
    }

    @Override
    public List<Price> findAllPrices() {
        try {
            return priceDao.findAllPrices();
        } catch (Exception e) {
            throw new WineCellarDataAccessException("Cannot find all prices", e);
        }
    }

    @Override
    public List<Price> findPriceByCurrency(Currency currency) {
        try{
            return priceDao.findPriceByCurrency(currency);
        }catch(Exception e){
            throw new WineCellarDataAccessException("Cannot find price by currency"
                    + currency, e);
        }
    }

    @Override
    public List<Price> findPriceByPriceAttribute(BigDecimal price) {
        try{
            return priceDao.findPriceByPriceAttribute(price);
        }catch(Exception e){
            throw new WineCellarDataAccessException("Cannot find price by price attribute"
                    + price, e);
        }
    }

    @Override
    public List<Price> findPriceByMarketingEvent(MarketingEvent event) {
        try{
            return priceDao.findPriceByMarketingEvent(event);
        }catch(Exception e){
            throw new WineCellarDataAccessException("Cannot find price by marketing event"
                    + event, e);
        }
    }

    @Override
    public List<Price> findPriceByPacking(Packing packing) {
        try{
            return priceDao.findPriceByPacking;
        }catch(Exception e){
            throw new WineCellarDataAccessException("Cannot find price by packing"
                    + packing, e);
        }
    }
}
