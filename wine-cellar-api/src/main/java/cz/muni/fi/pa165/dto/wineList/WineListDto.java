package cz.muni.fi.pa165.dto.wineList;

import cz.muni.fi.pa165.dto.marketingEvent.MarketingEventDto;
import cz.muni.fi.pa165.dto.wine.WineDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.10.29
 */
public class WineListDto {

    private Long id;

    private String name;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime date;

    private MarketingEventDto marketingEvent;

    private List<WineDto> wines = new ArrayList<>();

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public MarketingEventDto getMarketingEvent() {
        return marketingEvent;
    }

    public void setMarketingEvent(MarketingEventDto marketingEvent) {
        this.marketingEvent = marketingEvent;
    }

    public List<WineDto> getWines() {
        return wines;
    }

    public void setWines(List<WineDto> wines) {
        this.wines = wines;
    }

    public void addWine(WineDto wine) {
        this.wines.add(wine);
    }

    public void removeWine(WineDto wine) {
        this.wines.remove(wine);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WineListDto)) return false;

        WineListDto that = (WineListDto) o;

        return getId() != null && getId().equals(that.getId());

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
