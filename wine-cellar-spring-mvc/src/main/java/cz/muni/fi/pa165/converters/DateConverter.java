package cz.muni.fi.pa165.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Michaela Bamburová on 18.12.2016.
 */
public class DateConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String input) {
        if (input.isEmpty()) return null;
        try {
            String[] dateParsed = input.split("/");

            return LocalDateTime.of(Integer.valueOf(dateParsed[2]), Integer.valueOf(dateParsed[0]), Integer.valueOf(dateParsed[1]), Integer.valueOf(String.valueOf(LocalTime.now().getHour())), Integer.valueOf(String.valueOf(LocalTime.now().getMinute())));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new IllegalArgumentException("Format is not dd/mm/yyyy");
        }
    }
}
