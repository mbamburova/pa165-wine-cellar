package cz.muni.fi.pa165.dto.wineList;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Gordian on 12/11/2016.
 */
public class WineListCreateDto {

    @NotNull
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime date;

    private Long marketingEventId;

    private List<Long> winesIds = new ArrayList<>();

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

    public Long getMarketingEventId() {
        return marketingEventId;
    }

    public void setMarketingEventId(Long marketingEvent) {
        this.marketingEventId = marketingEvent;
    }

    public List<Long> getWinesIds() {
        return winesIds;
    }

    public void setWinesIds(List<Long> winesIds) {
        this.winesIds = winesIds;
    }

    public void addWine(Long wine) {
        this.winesIds.add(wine);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WineListCreateDto)) return false;

        WineListCreateDto that = (WineListCreateDto) o;

        if (!getName().equals(that.getName())) return false;
        if (!getDate().equals(that.getDate())) return false;
        if (getMarketingEventId() != null ? !getMarketingEventId().equals(that.getMarketingEventId()) : that.getMarketingEventId() != null) return false;
        return getWinesIds() != null ? getWinesIds().equals(that.getWinesIds()) : that.getWinesIds() == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (marketingEventId != null ? marketingEventId.hashCode() : 0);
        result = 31 * result + (winesIds != null ? winesIds.hashCode() : 0);
        return result;
    }
}
