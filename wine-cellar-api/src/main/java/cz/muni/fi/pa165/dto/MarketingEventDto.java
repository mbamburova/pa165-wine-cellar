package cz.muni.fi.pa165.dto;

/**
 * @author MarekScholtz
 * @version 2016.10.29
 */
public class MarketingEventDto {

    private Long id;
    private String description;

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
        if (o == null || getClass() != o.getClass()) return false;
        MarketingEventDto that = (MarketingEventDto) o;
        return !(this.getId() == null || that.getId() == null) && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

}
