package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PriceDao;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.exception.WineCellarDataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

@Service
public class PriceServiceImpl implements PriceService {

    @Inject
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
    public List<Price> findPricesByCurrency(Currency currency) {
        try{
            return priceDao.findPricesByCurrency(currency);
        }catch(Exception e){
            throw new WineCellarDataAccessException("Cannot find price by currency"
                    + currency, e);
        }
    }

    @Override
    public List<Price> findPricesByPriceAttribute(BigDecimal price) {
        try{
            return priceDao.findPricesByPriceAttribute(price);
        }catch(Exception e){
            throw new WineCellarDataAccessException("Cannot find price by price attribute"
                    + price, e);
        }
    }

    @Override
    public List<Price> findPricesByMarketingEvent(MarketingEvent event) {
        try{
            return priceDao.findPricesByMarketingEvent(event);
        }catch(Exception e){
            throw new WineCellarDataAccessException("Cannot find price by marketing event"
                    + event, e);
        }
    }

    @Override
    public List<Price> findPricesByPacking(Packing packing) {
        try{
            return priceDao.findPricesByPacking(packing);
        }catch(Exception e){
            throw new WineCellarDataAccessException("Cannot find price by packing"
                    + packing, e);
        }
    }
}
