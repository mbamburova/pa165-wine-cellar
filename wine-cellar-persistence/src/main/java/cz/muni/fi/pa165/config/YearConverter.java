package cz.muni.fi.pa165.config;

import javax.persistence.AttributeConverter;
import java.time.Year;

/**
 * @author Michaela Bamburová on 14.12.2016.
 */
public class YearConverter implements AttributeConverter<Year, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Year year) {
        return year.getValue();
    }

    @Override
    public Year convertToEntityAttribute(Integer integer) {
        return Year.of(integer);
    }
}
