package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Tomas Gordian on 10/30/2016.
 */
public class MarketingEvent {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "WINE_ID")
    private Long id;

    @NotNull
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MARKETING_EVENT_PRICE",
            joinColumns = { @JoinColumn(name = "MARKETING_EVENT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "PRICE_ID") })
    private List<Price> prices;

    public MarketingEvent(Long id) {
        this.id = id;
    }

    public MarketingEvent() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarketingEvent marketingEvent = (MarketingEvent) o;

        return getId() != null && getId().equals(marketingEvent.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

