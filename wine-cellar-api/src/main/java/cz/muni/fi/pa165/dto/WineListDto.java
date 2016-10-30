package cz.muni.fi.pa165.dto;

import org.joda.time.DateTime;

/**
 * @author MarekScholtz
 * @version 2016.10.29
 */
public class WineListDto {

    private Long id;
    private String name;
    private DateTime date;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WineListDto that = (WineListDto) o;
        return !(this.getId() == null || that.getId() == null) && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
