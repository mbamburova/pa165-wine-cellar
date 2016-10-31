package cz.muni.fi.pa165.entity;

import org.joda.time.DateTime;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @author Silvia Borzová
 *         25/10/2016
 */
@Entity
public class Packing {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal volume;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private DateTime validFrom;

    @Temporal(TemporalType.TIMESTAMP)
    private DateTime validTo;

    @NotNull
    @OneToMany
    @JoinColumn(name = "PRICE_ID")
    private List<Price> prices = new ArrayList<>();

    public Packing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public DateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(DateTime validFrom) {
        this.validFrom = validFrom;
    }

    public DateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(DateTime validTo) {
        this.validTo = validTo;
    }

    public List<Price> getPrices() {
        return Collections.unmodifiableList(prices);
    }

    public void addPrice(Price price) {
        prices.add(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Packing)) return false;

        Packing packing = (Packing) o;

        return getId() != null && getId().equals(packing.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
