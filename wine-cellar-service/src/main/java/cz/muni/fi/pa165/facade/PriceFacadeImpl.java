package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.PriceDto;
import cz.muni.fi.pa165.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

/**
 * @author Michaela Bamburová on 08.11.2016.
 */
@Service
@Transactional
public class PriceFacadeImpl implements PriceFacade {

    @Autowired
    private PriceService priceService;


    @Override
    public void createPrice(PriceDto price) {
    }

    @Override
    public void updatePrice(PriceDto price) {
    }

    @Override
    public void deletePrice(PriceDto price) {
    }

    @Override
    public List<PriceDto> findAllPrices() {
        return null;
    }

    @Override
    public PriceDto findPriceById(Long priceId) {
        return null;
    }

    @Override
    public List<PriceDto> findByMarketingEvent(MarketingEventDto marketingEvent) {
        return null;
    }

    @Override
    public List<PriceDto> findByPacking(PackingDto packing) {
        return null;
    }

    @Override
    public List<PriceDto> findByCurrency(Currency currency) {
        return null;
    }

    @Override
    public List<PriceDto> findByPrice(BigDecimal price) {
        return null;
    }
}
