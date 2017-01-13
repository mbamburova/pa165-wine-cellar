package cz.muni.fi.pa165.dto.price;

import cz.muni.fi.pa165.dto.packing.PackingCreateDto;

/**
 * @author Silvia Borzová
 *         17/12/2016
 */
public class PricePackingCreateDto {

    private PriceCreateDto priceDto;
    private PackingCreateDto packingDto;

    public PriceCreateDto getPriceDto() {
        return priceDto;
    }

    public void setPriceDto(PriceCreateDto priceDto) {
        this.priceDto = priceDto;
    }

    public PackingCreateDto getPackingDto() {
        return packingDto;
    }

    public void setPackingDto(PackingCreateDto packingDto) {
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
