package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.PackingCreateDto;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.WineDto;

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
  //  boolean isPackingValid(PackingDto p);

    PackingDto findPackingById(Long id);
    List<PackingDto> findAllPackings();
    List<PackingDto> findPackingsByVolume(BigDecimal volume);
    List<PackingDto> findPackingsByWine(WineDto wineDto);
}
