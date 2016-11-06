package cz.muni.fi.pa165.facade;

import java.math.BigDecimal;
import java.time.Year;

/**
 * @author MarekScholtz
 * @version 2016.11.06
 */
public interface WineFacade {

    Long create(WineDto wineDto);

    WineDto get(Long id);
    List<WineDto> getAll();

    void update(Long wineId);
    void updateName(Long wineId, String name);
    void updateVintage(Long wineId, Year vintage);
    void updateBatch(Long wineId, String batch);
    void updatePredicate(Long wineId, String predicate);
    void updatePredicateEquivalent(Long wineId, String predicateEquivalent);
    void updateDescription(Long wineId, String description);
    void updateNotes(Long wineId, String notes);
    void updateAlcoholVolume(Long wineId, BigDecimal alcoholVolume);
    void updateResidualSugar(Long wineId, BigDecimal residualSugar);
    void updateAcidity(Long wineId, BigDecimal acidity);
    void updateGrapeSugarContent(Long wineId, BigDecimal grapeSugarContent);

    void delete(Long wineId);

    List<WineDto> findByName(String name);
    List<WineDto> findByVintage(Year vintage);
    WineDto findByBatch(String batch);
    List<WineDto> findByPredicate(String predicate);
    List<WineDto> findByPredicateEquivalent(String predicateEquivalent);
    List<WineDto> findByDescription(String description);
    List<WineDto> findByNotes(String notes);
    List<WineDto> findByAlcoholVolume(BigDecimal alcoholVolume);
    List<WineDto> findByResidualSugar(BigDecimal residualSugar);
    List<WineDto> findByAcidity(BigDecimal acidity);
    List<WineDto> findByGrapeSugarContent(BigDecimal grapeSugarContent);
    List<WineDto> findBetweenYears(Year from, Year to);

    void addPackage(Long wine, Long packing);
    void removePackage(Long wine, Long packing);

}
