package cz.muni.fi.pa165.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Michaela Bamburová on 13.01.2017.
 */
public class FromDateConverter implements Converter<LocalDateTime, String> {

    @Override
    public String convert(LocalDateTime localDateTime) {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = localDateTime.format(format);

        return date;
    }
}
