package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.converters.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

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

   // @NotNull
   // @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "WINELIST_WINE",
            joinColumns = { @JoinColumn(name = "WINELIST_ID") },
            inverseJoinColumns = { @JoinColumn(name = "WINE_ID") })
    private List<Wine> wines = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private MarketingEvent marketingEvent;

    public WineList(Long id) {
        this.id = id;
    }

    public WineList() {
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Wine> getWines() {
        return wines;
    }

    public void setWines(List<Wine> wines) {
        this.wines = wines;
    }

    public void addWine(Wine wine) {
        this.wines.add(wine);
    }

    public void removeWine(Wine wine) {
        this.wines.remove(wine);
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
        if (!(o instanceof WineList)) return false;

        WineList wineList = (WineList) o;

        return getId() != null && getId().equals(wineList.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
