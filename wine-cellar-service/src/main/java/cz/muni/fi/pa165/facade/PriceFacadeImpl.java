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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.NoResultException;
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


    @Override
    public void createPrice(PriceCreateDto priceCreateDto) {
        if (priceCreateDto == null) {
            throw new IllegalArgumentException("PriceCreateDto is null!");
        }
        Price price = new Price();
        price.setPrice(priceCreateDto.getPrice());
        price.setCurrency(priceCreateDto.getCurrency());
        price.setPacking(packingService.findPackingById(priceCreateDto.getPackingId()));
        price.setMarketingEvent(marketingEventService.findMarketingEventById(priceCreateDto.getMarketingEventId()));
        priceService.createPrice(price);
    }

    @Override
    public void updatePrice(PriceDto priceDto) {
        Price price = new Price();
        price.setPrice(priceDto.getPrice());
        price.setCurrency(priceDto.getCurrency());
        price.setPacking(beanMappingService.mapTo(priceDto.getPacking(), Packing.class));
        if (priceDto.getMarketingEvent() != null) {
            price.setMarketingEvent(beanMappingService.mapTo(priceDto.getMarketingEvent(), MarketingEvent.class));
        }
        priceService.updatePrice(price);
    }

    @Override
    public void deletePrice(PriceDto priceDto) {
        Price price = new Price();
        price.setPrice(priceDto.getPrice());
        price.setCurrency(priceDto.getCurrency());
        price.setPacking(beanMappingService.mapTo(priceDto.getPacking(), Packing.class));
        if (priceDto.getMarketingEvent() != null) {
            price.setMarketingEvent(beanMappingService.mapTo(priceDto.getMarketingEvent(), MarketingEvent.class));
        }
        priceService.deletePrice(price);
    }

    @Override
    public List<PriceDto> findAllPrices() {
        List<PriceDto> prices = new ArrayList<>();
        for (Price price : priceService.findAllPrices()) {
            PriceDto priceDto = new PriceDto();
            priceDto.setId(price.getId());
            priceDto.setCurrency(price.getCurrency());
            priceDto.setPacking(beanMappingService.mapTo(price.getPacking(), PackingDto.class));
            if (price.getMarketingEvent() != null) {
                priceDto.setMarketingEvent(beanMappingService.mapTo(price.getMarketingEvent(), MarketingEventDto.class));
            }
            prices.add(priceDto);
        }
        return prices;
    }

    @Override
    public PriceDto findPriceById(Long priceId) {
        if (priceService.findPriceById(priceId) == null) {
            throw new NoResultException();
        }
        Price price = priceService.findPriceById(priceId);
        PriceDto priceDto = new PriceDto();
        priceDto.setId(price.getId());
        priceDto.setCurrency(price.getCurrency());
        priceDto.setPacking(beanMappingService.mapTo(price.getPacking(), PackingDto.class));
        if (price.getMarketingEvent() != null) {
            priceDto.setMarketingEvent(beanMappingService.mapTo(price.getMarketingEvent(), MarketingEventDto.class));
        }
        return priceDto;
    }

    @Override
    public List<PriceDto> findPricesByMarketingEvent(MarketingEventDto marketingEventDto) {
        MarketingEvent mappedMarketingEvent = beanMappingService.mapTo(marketingEventDto, MarketingEvent.class);

        if (priceService.findPricesByMarketingEvent(mappedMarketingEvent) == null) {
            throw new NoResultException();
        }
        List<PriceDto> prices = new ArrayList<>();
        for (Price price : priceService.findPricesByMarketingEvent(beanMappingService.mapTo(marketingEventDto, MarketingEvent.class))) {
            PriceDto priceDto = new PriceDto();
            priceDto.setId(price.getId());
            priceDto.setCurrency(price.getCurrency());
            priceDto.setPacking(beanMappingService.mapTo(price.getPacking(), PackingDto.class));
            if (price.getMarketingEvent() != null) {
                priceDto.setMarketingEvent(beanMappingService.mapTo(price.getMarketingEvent(), MarketingEventDto.class));
            }
            prices.add(priceDto);
        }
        return prices;
    }

    @Override
    public List<PriceDto> findPricesByPacking(PackingDto packingDto) {
        Packing mappedPacking = beanMappingService.mapTo(packingDto, Packing.class);

        if (priceService.findPricesByPacking(mappedPacking) == null) {
            throw new NoResultException();
        }
        List<PriceDto> prices = new ArrayList<>();
        for (Price price : priceService.findPricesByPacking(beanMappingService.mapTo(packingDto, Packing.class))) {
            PriceDto priceDto = new PriceDto();
            priceDto.setId(price.getId());
            priceDto.setCurrency(price.getCurrency());
            priceDto.setPacking(beanMappingService.mapTo(price.getPacking(), PackingDto.class));
            if (price.getMarketingEvent() != null) {
                priceDto.setMarketingEvent(beanMappingService.mapTo(price.getMarketingEvent(), MarketingEventDto.class));
            }
            prices.add(priceDto);
        }
        return prices;
    }

    @Override
    public List<PriceDto> findPricesByCurrency(Currency currency) {
        if (priceService.findPricesByCurrency(currency) == null) {
            throw new NoResultException();
        }
        List<PriceDto> prices = new ArrayList<>();
        for (Price price : priceService.findPricesByCurrency(currency)) {
            PriceDto priceDto = new PriceDto();
            priceDto.setId(price.getId());
            priceDto.setCurrency(price.getCurrency());
            priceDto.setPacking(beanMappingService.mapTo(price.getPacking(), PackingDto.class));
            if (price.getMarketingEvent() != null) {
                priceDto.setMarketingEvent(beanMappingService.mapTo(price.getMarketingEvent(), MarketingEventDto.class));
            }
            prices.add(priceDto);
        }
        return prices;
    }

    @Override
    public List<PriceDto> findPricesByPrice(BigDecimal value) {
        if (priceService.findPricesByPriceAttribute(value) == null) {
            throw new NoResultException();
        }
        List<PriceDto> prices = new ArrayList<>();
        for (Price price : priceService.findPricesByPriceAttribute(value)) {
            PriceDto priceDto = new PriceDto();
            priceDto.setId(price.getId());
            priceDto.setCurrency(price.getCurrency());
            priceDto.setPacking(beanMappingService.mapTo(price.getPacking(), PackingDto.class));
            if (price.getMarketingEvent() != null) {
                priceDto.setMarketingEvent(beanMappingService.mapTo(price.getMarketingEvent(), MarketingEventDto.class));
            }
            prices.add(priceDto);
        }
        return prices;
    }
}
