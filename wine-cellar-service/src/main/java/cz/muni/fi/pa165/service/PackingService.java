package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.11.06
 */

public interface PackingService {

    void create(Packing packing);

    Packing get(Long id);
    List<Packing> getAll();

    void update(Packing packing);
    void updateVolume(Packing packing, BigDecimal volume);
    void updateValidFrom(Packing packing, LocalDateTime validFrom);
    void updateValidTo(Packing packing, LocalDateTime validTo);

    void delete(Packing packing);

    boolean isValid(Packing packing);

    void addPrice(Packing packing, Price price);
    void removePrice(Packing packing, Price price);

}
