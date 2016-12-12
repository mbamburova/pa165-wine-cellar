package cz.muni.fi.pa165.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Michaela Bamburová on 20.11.2016.
 */
public class PackingCreateDto {

    private Long id;

    @NotNull
    @Min(0)
    private BigDecimal volume;

    @NotNull
    private String validFrom;

    private String validTo;

    @NotNull
    private Long wineId;

    public PackingCreateDto() {
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    public Long getWineId() {
        return wineId;
    }

    public void setWineId(Long wineId) {
        this.wineId = wineId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
