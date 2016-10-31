package cz.muni.fi.pa165.entity;

import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Tomas Gordian on 10/30/2016.
 */
@Entity
public class WineList {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "WINELIST_ID")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private DateTime date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "WINELIST_WINE",
            joinColumns = { @JoinColumn(name = "WINELIST_ID") },
            inverseJoinColumns = { @JoinColumn(name = "WINE_ID") })
    private List<Wine> wines = new ArrayList<>();

    @ManyToOne
    private MarketingEvent marketingEvent;

    public WineList(Long id) {
        this.id = id;
    }

    public WineList() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public List<Wine> getWines() {
        return Collections.unmodifiableList(wines);
    }

    public void setWines(List<Wine> wines) {
        this.wines = wines;
    }

    public MarketingEvent getMarketingEvent() {
        return marketingEvent;
    }

    public void setMarketingEvent(MarketingEvent marketingEvent) {
        this.marketingEvent = marketingEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WineList wineList = (WineList) o;

        return getId() != null && getId().equals(wineList.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
