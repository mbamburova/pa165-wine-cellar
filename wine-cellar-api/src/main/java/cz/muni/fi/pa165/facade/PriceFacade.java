package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.PriceDto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

/**
 * @author Michaela Bamburová on 08.11.2016.
 */
public interface PriceFacade {

    void createPrice(PriceCreateDto price);
    void updatePrice(PriceDto price);
    void deletePrice(PriceDto price);

    List<PriceDto> findAllPrices();
    PriceDto findPriceById(Long priceId);
    List<PriceDto> findByMarketingEvent(MarketingEventDto marketingEvent);
    List<PriceDto> findByPacking(PackingDto packing);
    List<PriceDto> findByCurrency(Currency currency);
    List<PriceDto> findByPrice(BigDecimal price);
}
