package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    public MarketingEvent(Long id) {
        this.id = id;
    }

    public MarketingEvent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return getId() != null ? getId().hashCode() : 0;
    }
}

