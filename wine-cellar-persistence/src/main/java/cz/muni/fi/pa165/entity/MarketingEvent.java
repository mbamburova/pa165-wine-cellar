package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Gordian on 10/30/2016.
 */
@Entity
public class MarketingEvent {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "MARKETING_EVENT_ID")
    private Long id;

    @NotNull
    private String description;

    @OneToMany(mappedBy = "MARKETING_EVENT")
    private List<Price> prices = new ArrayList<>();

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
        if (!(o instanceof MarketingEvent)) return false;

        MarketingEvent marketingEvent = (MarketingEvent) o;

        return getId() != null && getId().equals(marketingEvent.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

