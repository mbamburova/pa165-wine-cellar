package cz.muni.fi.pa165.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Tomas Gordian on 11/25/2016.
 */
public class PriceCreateDto {

    @NotNull
    private BigDecimal price;

    @NotNull
    private Currency currency;
    private Long packingId;
    private Long marketingEventid;

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

    public Long getPackingId() {
        return packingId;
    }

    public void setPackingId(Long packingId) {
        this.packingId = packingId;
    }

    public Long getMarketingEventid() {
        return marketingEventid;
    }

    public void setMarketingEventid(Long marketingEventid) {
        this.marketingEventid = marketingEventid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceCreateDto)) return false;

        PriceCreateDto that = (PriceCreateDto) o;

        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (packingId != null ? !packingId.equals(that.packingId) : that.packingId != null) return false;
        return marketingEventid != null ? marketingEventid.equals(that.marketingEventid) : that.marketingEventid == null;

    }

    @Override
    public int hashCode() {
        int result = price != null ? price.hashCode() : 0;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (packingId != null ? packingId.hashCode() : 0);
        result = 31 * result + (marketingEventid != null ? marketingEventid.hashCode() : 0);
        return result;
    }
}
