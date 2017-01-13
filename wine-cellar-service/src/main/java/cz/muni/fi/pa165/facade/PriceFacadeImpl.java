package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.marketingEvent.MarketingEventDto;
import cz.muni.fi.pa165.dto.packing.PackingDto;
import cz.muni.fi.pa165.dto.price.PriceCreateDto;
import cz.muni.fi.pa165.dto.price.PriceDto;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * @author Michaela Bamburová on 08.11.2016.
 */
@Service
@Transactional
public class PriceFacadeImpl implements PriceFacade {

    @Inject
    private BeanMappingService beanMappingService;

    @Inject
    private PriceService priceService;

    @Inject
    private MarketingEventService marketingEventService;

    @Inject
    private PackingService packingService;

    @Inject
    private WineService wineService;

    @Override
    public Long createPrice(PriceCreateDto priceDto) {
        if (priceDto == null) {
            throw new IllegalArgumentException("PriceDto cannot be null");
        }
        Price price = beanMappingService.mapTo(priceDto, Price.class);
        if (priceDto.getMarketingEventId() != null) {
            price.setMarketingEvent(marketingEventService.findMarketingEventById(priceDto.getMarketingEventId()));
        }
        price.setPacking(packingService.findPackingById(priceDto.getPackingId()));
        priceService.createPrice(price);
        return price.getId();
    }

    @Override
    public void updatePrice(PriceDto priceDto) {
        if (priceDto == null) {
            throw new IllegalArgumentException("PriceDto cannot be null");
        }
        Price price = beanMappingService.mapTo(priceDto, Price.class);
        if (priceDto.getMarketingEvent() != null){
            price.setMarketingEvent(marketingEventService.findMarketingEventById(priceDto.getMarketingEvent().getId()));
        } else price.setMarketingEvent(null);

        price.setPacking(packingService.findPackingById(priceDto.getPacking().getId()));
        priceService.updatePrice(price);
    }

    @Override
    public void deletePrice(Long priceId) {
        if (priceId == null) {
            throw new IllegalArgumentException("Price ID cannot be null");
        }
        Price price = priceService.findPriceById(priceId);
        priceService.deletePrice(price);
    }

    @Override
    public List<PriceDto> findAllPrices() {
        return beanMappingService.mapToCollection(priceService.findAllPrices(), PriceDto.class);
    }

    @Override
    public PriceDto findPriceById(Long priceId) {
        if (priceId == null) {
            throw new IllegalArgumentException("Price ID is null!");
        }
        Price price = priceService.findPriceById(priceId);
        if (price == null) {
            throw new EntityNotFoundException("Price was not found by ID.");
        }
        return beanMappingService.mapTo(price, PriceDto.class);
    }

    @Override
    public List<PriceDto> findPricesByMarketingEvent(MarketingEventDto marketingEventDto) {
        MarketingEvent mappedMarketingEvent = beanMappingService.mapTo(marketingEventDto, MarketingEvent.class);
        List<Price> prices = priceService.findPricesByMarketingEvent(mappedMarketingEvent);

        return beanMappingService.mapToCollection(prices, PriceDto.class);
    }

    @Override
    public List<PriceDto> findPricesByPacking(PackingDto packingDto) {
        if (packingDto == null) {
            throw new IllegalArgumentException("Price packing cannot be null");
        }
        Packing mappedPacking = beanMappingService.mapTo(packingDto, Packing.class);
        mappedPacking.setWine(wineService.findWineById(packingDto.getWine().getId()));
        List<PriceDto> priceDtos = new ArrayList<>();

        for (Price price : priceService.findPricesByPacking(mappedPacking)) {
            priceDtos.add(beanMappingService.mapTo(price, PriceDto.class));
        }
        return priceDtos;
    }

    @Override
    public List<PriceDto> findPricesByCurrency(Currency currency) {
        if (currency == null) {
            throw new IllegalArgumentException("Price currency cannot be null");
        }
        List<PriceDto> priceDtos = new ArrayList<>();
        for (Price price : priceService.findPricesByCurrency(currency)) {
            priceDtos.add(beanMappingService.mapTo(price, PriceDto.class));
        }
        return priceDtos;
    }

    @Override
    public List<PriceDto> findPricesByPrice(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("Price value cannot be null");
        }
        List<PriceDto> priceDtos = new ArrayList<>();
        for (Price price : priceService.findPricesByPriceAttribute(value)) {
            priceDtos.add(beanMappingService.mapTo(price, PriceDto.class));
        }
        return priceDtos;
    }
}
