package cz.muni.fi.pa165.dto.price;

import cz.muni.fi.pa165.dto.marketingEvent.MarketingEventDto;
import cz.muni.fi.pa165.dto.packing.PackingDto;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public class PriceDto {

    private Long id;

    private BigDecimal price;

    private Currency currency;

    private PackingDto packing;

    private MarketingEventDto marketingEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public MarketingEventDto getMarketingEvent() {
        return marketingEvent;
    }

    public void setMarketingEvent(MarketingEventDto marketingEvent) {
        this.marketingEvent = marketingEvent;
    }

    public PackingDto getPacking() {
        return packing;
    }

    public void setPacking(PackingDto packing) {
        this.packing = packing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceDto)) return false;

        PriceDto priceDto = (PriceDto) o;

        return getId() != null && getId().equals(priceDto.getId());

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
