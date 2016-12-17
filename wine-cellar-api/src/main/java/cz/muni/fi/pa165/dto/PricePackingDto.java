package cz.muni.fi.pa165.dto;

/**
 * @author Michaela Bamburová on 15.12.2016.
 */
public class PricePackingDto {

    private PriceDto priceDto;

    private PackingDto packingDto;

    public PriceDto getPriceDto() {
        return priceDto;
    }

    public void setPriceDto(PriceDto priceDto) {
        this.priceDto = priceDto;
    }

    public PackingDto getPackingDto() {
        return packingDto;
    }

    public void setPackingDto(PackingDto packingDto) {
        this.packingDto = packingDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PricePackingDto)) return false;

        PricePackingDto that = (PricePackingDto) o;

        return (getPriceDto() != null ? getPriceDto().equals(that.getPriceDto()) : that.getPriceDto() == null) && (getPackingDto() != null ? getPackingDto().equals(that.getPackingDto()) : that.getPackingDto() == null);
    }

    @Override
    public int hashCode() {
        int result = getPriceDto() != null ? getPriceDto().hashCode() : 0;
        result = 31 * result + (getPackingDto() != null ? getPackingDto().hashCode() : 0);
        return result;
    }
}