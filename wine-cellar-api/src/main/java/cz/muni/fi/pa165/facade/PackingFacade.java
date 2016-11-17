package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.WineDto;
import org.joda.time.LocalDateTime;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

public interface PackingFacade {

    void createPacking(PackingDto p);
    void updatePacking(PackingDto p);
    void deletePacking(PackingDto p);

    PackingDto findPackingById(Long id);
    List<PackingDto> findAllPackings();
    List<PackingDto> findPackingByVolume(BigDecimal volume);
    List<PackingDto> findPackingByValidFrom(LocalDateTime validFrom);
    List<PackingDto> findPackingByValidTo(LocalDateTime validTo);
    List<PackingDto> findPackingByWine(WineDto wineDto);
    List<PackingDto> findPackingValidForDate(LocalDateTime dateTime);
}
