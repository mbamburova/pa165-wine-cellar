package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.WineDto;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.11.06
 */
public interface WineFacade {

    Long createWine(WineDto wineDto);

    WineDto findWineById(Long id);
    List<WineDto> findAllWines();

    void updateWine(Long wineId);
    void updateWineName(Long wineId, String name);
    void updateWineVintage(Long wineId, Year vintage);
    void updateWineBatch(Long wineId, String batch);
    void updateWinePredicate(Long wineId, String predicate);
    void updateWinePredicateEquivalent(Long wineId, String predicateEquivalent);
    void updateWineDescription(Long wineId, String description);
    void updateWineNotes(Long wineId, String notes);
    void updateWineAlcoholVolume(Long wineId, BigDecimal alcoholVolume);
    void updateWineResidualSugar(Long wineId, BigDecimal residualSugar);
    void updateWineAcidity(Long wineId, BigDecimal acidity);
    void updateWineGrapeSugarContent(Long wineId, BigDecimal grapeSugarContent);

    void deleteWine(Long wineId);

    List<WineDto> findWinesByName(String name);
    List<WineDto> findWinesByVintage(Year vintage);
    WineDto findWineByBatch(String batch);
    List<WineDto> findWinesByPredicate(String predicate);
    List<WineDto> findWinesByPredicateEquivalent(String predicateEquivalent);
    List<WineDto> findWinesByDescription(String description);
    List<WineDto> findWinesByNotes(String notes);
    List<WineDto> findWinesByAlcoholVolume(BigDecimal alcoholVolume);
    List<WineDto> findWinesByResidualSugar(BigDecimal residualSugar);
    List<WineDto> findWinesByAcidity(BigDecimal acidity);
    List<WineDto> findWinesByGrapeSugarContent(BigDecimal grapeSugarContent);
    List<WineDto> findWinesBetweenYears(Year from, Year to);

    void addWinePackage(Long wine, Long packing);
    void removeWinePackage(Long wine, Long packing);

}
