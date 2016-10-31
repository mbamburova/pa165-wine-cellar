package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author MarekScholtz
 * @version 2016.10.25
 */
@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @NotNull
    //@Enumerated
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="MARKETING_EVENT_ID")
    private MarketingEvent marketingEvent;

    public Price() {
    }

    public Price(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public MarketingEvent getMarketingEvent() {
        return marketingEvent;
    }

    public void setMarketingEvent(MarketingEvent marketingEvent) {
        this.marketingEvent = marketingEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return !(this.getId() == null || price.getId() == null) && id.equals(price.id);
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}