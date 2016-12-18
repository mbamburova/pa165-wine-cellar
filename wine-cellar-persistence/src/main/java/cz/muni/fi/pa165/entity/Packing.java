package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.converters.LocalDateTimeConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime validFrom;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime validTo;

    @ManyToOne(optional = false)
    private Wine wine;

    public Packing() {
    }

    public Packing(Long id) {
        this.id = id;
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

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }

    public Wine getWine() {
        return wine;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
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
        return getId() != null ? getId().hashCode() : 0;
    }
}
