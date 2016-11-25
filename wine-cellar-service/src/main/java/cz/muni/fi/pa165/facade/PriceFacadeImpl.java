package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.PriceCreateDto;
import cz.muni.fi.pa165.dto.PriceDto;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.MarketingEventService;
import cz.muni.fi.pa165.service.PackingService;
import cz.muni.fi.pa165.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
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
    private BeanMappingService beanMappingService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private MarketingEventService marketingEventService;

    @Autowired
    private PackingService packingService;


    @Override
    public void createPrice(PriceCreateDto priceCreateDto) {
        if (priceCreateDto == null) {
            throw new IllegalArgumentException("PriceCreateDto is null!");
        }
        Price mappedPrice = beanMappingService.mapTo(priceCreateDto, Price.class);
        mappedPrice.setMarketingEvent(marketingEventService.findMarketingEventById(priceCreateDto.getMarketingEventId()));
        mappedPrice.setPacking(packingService.findPackingById(priceCreateDto.getPackingId()));
        priceService.createPrice(mappedPrice);
    }

    @Override
    public void updatePrice(PriceDto priceDto) {
        Price mappedPrice = beanMappingService.mapTo(priceDto, Price.class);
        priceService.updatePrice(mappedPrice);
    }

    @Override
    public void deletePrice(PriceDto priceDto) {
        Price mappedPrice = beanMappingService.mapTo(priceDto, Price.class);
        priceService.deletePrice(mappedPrice);
    }

    @Override
    public List<PriceDto> findAllPrices() {
        return beanMappingService.mapToCollection(priceService.findAllPrices(), PriceDto.class);
    }

    @Override
    public PriceDto findPriceById(Long priceId) {
        if (priceService.findPriceById(priceId) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapTo(priceService.findPriceById(priceId), PriceDto.class);
    }

    @Override
    public List<PriceDto> findPricesByMarketingEvent(MarketingEventDto marketingEventDto) {
        MarketingEvent mappedMarketingEvent = beanMappingService.mapTo(marketingEventDto, MarketingEvent.class);

        if (priceService.findPriceByMarketingEvent(mappedMarketingEvent) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(priceService.findPriceByMarketingEvent(mappedMarketingEvent), PriceDto.class);
    }

    @Override
    public List<PriceDto> findPricesByPacking(PackingDto packingDto) {
        Packing mappedPacking = beanMappingService.mapTo(packingDto, Packing.class);

        if (priceService.findPriceByPacking(mappedPacking) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(priceService.findPriceByPacking(mappedPacking), PriceDto.class);
    }

    @Override
    public List<PriceDto> findPricesByCurrency(Currency currency) {
        if (priceService.findPriceByCurrency(currency) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(priceService.findPriceByCurrency(currency), PriceDto.class);
    }

    @Override
    public List<PriceDto> findPricesByPrice(BigDecimal price) {
        if (priceService.findPriceByPriceAttribute(price) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(priceService.findPriceByPriceAttribute(price), PriceDto.class);
    }
}
