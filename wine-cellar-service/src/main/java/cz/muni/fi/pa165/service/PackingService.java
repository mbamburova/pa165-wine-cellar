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

    void createPacking(Packing packing);

    Packing findPackingById(Long id);
    List<Packing> findAllPackings();

    void updatePacking(Packing packing);
    void updatePackingVolume(Packing packing, BigDecimal volume);
    void updatePackingValidFrom(Packing packing, LocalDateTime validFrom);
    void updatePackingValidTo(Packing packing, LocalDateTime validTo);

    void deletePacking(Packing packing);

    boolean isPackingValid(Packing packing);

    void addPackingPrice(Packing packing, Price price);
    void removePackingPrice(Packing packing, Price price);

}
