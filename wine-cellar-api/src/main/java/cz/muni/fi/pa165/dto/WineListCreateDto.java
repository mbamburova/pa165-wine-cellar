package cz.muni.fi.pa165.dto;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Gordian on 12/11/2016.
 */
public class WineListCreateDto {

    private Long id;
    private String name;
    private String date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
        if (o == null || getClass() != o.getClass()) return false;

        WineListCreateDto that = (WineListCreateDto) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (marketingEvent != null ? !marketingEvent.equals(that.marketingEvent) : that.marketingEvent != null)
            return false;
        return wines != null ? wines.equals(that.wines) : that.wines == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (marketingEvent != null ? marketingEvent.hashCode() : 0);
        result = 31 * result + (wines != null ? wines.hashCode() : 0);
        return result;
    }
}
