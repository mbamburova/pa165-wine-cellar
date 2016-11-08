package cz.muni.fi.pa165.dto;

import org.joda.time.DateTime;
import java.math.BigDecimal;

/**
 * @author Michaela Bamburová on 25.10.2016.
 */
public class PackingDto {

    private Long id;
    private BigDecimal volume;
    private DateTime validFrom;
    private DateTime validTo;

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
