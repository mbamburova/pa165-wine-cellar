package cz.muni.fi.pa165.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Michaela Bamburová on 25.10.2016.
 */
public class PackingDto {

    private Long id;

    private BigDecimal volume;

    private LocalDateTime validFrom;

    private LocalDateTime validTo;

    private WineDto wine;

    public PackingDto() {
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

    public WineDto getWine() {
        return wine;
    }

    public void setWine(WineDto wine) {
        this.wine = wine;
    }

    @Override
    public String toString() {
        return "PackingDto{" + "id=" + id + ", volume=" + volume + ", validFrom=" +
            validFrom + ", validTo=" + validTo + ", wine=" + wine + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackingDto)) return false;

        PackingDto that = (PackingDto) o;

        return getId() != null && getId().equals(that.getId());

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
