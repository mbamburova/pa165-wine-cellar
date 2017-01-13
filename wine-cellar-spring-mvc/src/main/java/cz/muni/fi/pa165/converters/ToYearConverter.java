package cz.muni.fi.pa165.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.Year;

/**
 * @author Michaela Bamburová on 13.01.2017.
 */
public class ToYearConverter implements Converter<String, Year> {

    @Override
    public Year convert(String input) {
        if (input.isEmpty()) {
            return null;
        }
        int year = Integer.parseInt(input);
        return Year.of(year);
    }
}
