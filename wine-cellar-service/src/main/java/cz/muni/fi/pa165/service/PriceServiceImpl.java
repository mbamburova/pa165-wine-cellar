package cz.muni.fi.pa165.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import cz.muni.fi.pa165.dao.PriceDao;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import org.dozer.inject.Inject;
import org.springframework.stereotype.Service;

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
        priceDao.createPrice(p);
    }

    @Override
    public void updatePrice(Price p) {
        priceDao.updatePrice(p);
    }

    @Override
    public void deletePrice(Price p) {
        priceDao.deletePrice(p);
    }

    @Override
    public Price findPriceById(Long id) {
        return priceDao.get(id);
    }

    @Override
    public List<Price> findAllPrices() {
        return priceDao.getAll();
    }

    @Override
    public List<Price> findPriceByCurrency(Currency currency) {
        return priceDao.getByCurrency(currency);
    }

    @Override
    public List<Price> findPriceByPriceAttribute(BigDecimal price) {
        return priceDao.getByPrice(price);
    }

    @Override
    public List<Price> findPriceByMarketingEvent(MarketingEvent event) {
        return priceDao.getByMarketingEvent(event);
    }

    @Override
    public List<Price> findPriceByPacking(Packing packing) {
        return priceDao.findPriceByPacking;
    }
}
