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

    void deleteWine(Long wineId);

    List<WineDto> findWinesByName(String name);
    List<WineDto> findWinesByVintage(Year vintage);
    WineDto findWineByBatch(String batch);
    List<WineDto> findWinesByPredicate(String predicate);
    List<WineDto> findWinesByPredicateEquivalent(String predicateEquivalent);
    List<WineDto> findWinesByAlcoholVolume(BigDecimal alcoholVolume);
    List<WineDto> findWinesByResidualSugar(BigDecimal residualSugar);
    List<WineDto> findWinesByAcidity(BigDecimal acidity);
    List<WineDto> findWinesByGrapeSugarContent(BigDecimal grapeSugarContent);
    List<WineDto> findWinesBetweenYears(Year from, Year to);
}
