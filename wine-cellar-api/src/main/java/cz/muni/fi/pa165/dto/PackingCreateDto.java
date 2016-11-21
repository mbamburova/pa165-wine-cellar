package cz.muni.fi.pa165.dto;

import org.joda.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Michaela Bamburová on 20.11.2016.
 */
public class PackingCreateDto {

    @NotNull
    @Min(0)
    private BigDecimal volume;

    @NotNull
    private LocalDateTime validFrom;

    private LocalDateTime validTo;

    @NotNull
    private Long wineId;

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

    public Long getWineId() {
        return wineId;
    }

    public void setWineId(Long wineId) {
        this.wineId = wineId;
    }

    @Override
    public String toString() {
        return "PackingCreateDto{" + "volume=" + volume + "," +
            " validFrom=" + validFrom + ", validTo=" + validTo + ", wineId=" + wineId + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackingCreateDto)) return false;

        PackingCreateDto that = (PackingCreateDto) o;

        if (!getVolume().equals(that.getVolume())) return false;
        if (!getValidFrom().equals(that.getValidFrom())) return false;
        if (getValidTo() != null && getValidTo().equals(that.getValidTo())) return false;
        return getWineId().equals(that.getWineId());
    }

    @Override
    public int hashCode() {
        int result = getVolume().hashCode();
        result = 31 * result + getValidFrom().hashCode();
        result = 31 * result + (getValidTo() != null ? getValidTo().hashCode() : 0);
        result = 31 * result + getWineId().hashCode();
        return result;
    }
}
