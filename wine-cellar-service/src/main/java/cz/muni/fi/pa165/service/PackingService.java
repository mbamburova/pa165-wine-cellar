package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.entity.Wine;
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
    List<Packing> findPackingsByVolume(BigDecimal volume);
    List<Packing> findPackingsByWine(Wine wine);

    void updatePacking(Packing packing);

    void deletePacking(Packing packing);

   // boolean isPackingValid(Packing packing);
}
