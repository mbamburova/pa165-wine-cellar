package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.packing.PackingCreateDto;
import cz.muni.fi.pa165.dto.packing.PackingDto;
import cz.muni.fi.pa165.dto.wine.WineDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

public interface PackingFacade {

    Long createPacking(PackingCreateDto p);

    void updatePacking(PackingDto p);

    void deletePacking(Long packingId);
    //  boolean isPackingValid(PackingDto p);

    PackingDto findPackingById(Long id);

    List<PackingDto> findAllPackings();

    List<PackingDto> findPackingsByVolume(BigDecimal volume);

    List<PackingDto> findPackingsByWine(WineDto wineDto);
}
