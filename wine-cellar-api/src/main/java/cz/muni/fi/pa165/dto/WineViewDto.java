package cz.muni.fi.pa165.dto;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michaela Bamburová on 15.12.2016.
 */
public class WineViewDto {

    private Long id;

    private String name;

    private Year vintage;

    private String predicate;

    private BigDecimal alcoholVolume;

    private List<PricePackingDto> pricePackingList = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getVintage() {
        return vintage;
    }

    public void setVintage(Year vintage) {
        this.vintage = vintage;
    }

    public String getPredicate() {
        return predicate;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    public BigDecimal getAlcoholVolume() {
        return alcoholVolume;
    }

    public void setAlcoholVolume(BigDecimal alcoholVolume) {
        this.alcoholVolume = alcoholVolume;
    }

    public List<PricePackingDto> getPricePackingList() {
        return pricePackingList;
    }

    public void addPricePacking(PricePackingDto pricePackingDto) {
        pricePackingList.add(pricePackingDto);
    }

    public void removePacking(PricePackingDto pricePackingDto) {
        pricePackingList.remove(pricePackingDto);
    }

    public void setPricePackingList(List<PricePackingDto> pricePackingList) {
        this.pricePackingList = pricePackingList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WineDto)) return false;

        WineDto wineDto = (WineDto) o;

        return getId() != null && getId().equals(wineDto.getId());

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "WineViewDto{" + "id=" + id + ", name='" + name + '\'' + ", vintage=" + vintage + "," +
            " predicate='" + predicate + '\'' + ", alcoholVolume=" + alcoholVolume + ", pricePackingList=" + pricePackingList + '}';
    }
}
