package cz.muni.fi.pa165.dto;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public class PriceDto {

    private Long id;
    private BigDecimal price;
    private Currency currency;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceDto priceDto = (PriceDto) o;

        return id.equals(priceDto.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
