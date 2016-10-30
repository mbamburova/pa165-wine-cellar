package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tomas on 10/30/2016.
 */
public class WineList {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "WINE_ID")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "WINELIST_WINE",
            joinColumns = { @JoinColumn(name = "WINELIST_ID") },
            inverseJoinColumns = { @JoinColumn(name = "WINE_ID") })
    private List<Wine> wines = new ArrayList<>();

    public WineList(Long id) {
        this.id = id;
    }

    public WineList() {
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Wine> getWines() {
        return wines;
    }

    public void setWines(List<Wine> wines) {
        this.wines = wines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WineList wineList = (WineList) o;

        return id.equals(wineList.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
