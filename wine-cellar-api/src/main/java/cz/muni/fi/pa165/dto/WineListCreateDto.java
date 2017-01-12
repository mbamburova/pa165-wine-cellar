package cz.muni.fi.pa165.dto;

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
        if (o == null || getClass() != o.getClass()) return false;

        WineListCreateDto that = (WineListCreateDto) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (marketingEventId != null ? !marketingEventId.equals(that.marketingEventId) : that.marketingEventId != null)
            return false;
        return winesIds != null ? winesIds.equals(that.winesIds) : that.winesIds == null;

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
