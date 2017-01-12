package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.WineCreateDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.dto.WineUpdateDto;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.11.06
 */
public interface WineFacade {

    Long createWine(WineCreateDto wineDto);

    void updateWine(WineUpdateDto wineDto);

    void deleteWine(Long wineId);

    WineDto findWineById(Long id);
    List<WineDto> findAllWines();
    List<WineDto> findWinesByName(String name);
    List<WineDto> findWinesByVintage(Year vintage);
    WineDto findWineByBatch(String batch);
    List<WineDto> findWinesByPredicate(String predicate);
    List<WineDto> findWinesByPredicateEquivalent(String predicateEquivalent);
    List<WineDto> findWinesByAlcoholVolume(BigDecimal fromAlcoholVolume, BigDecimal toAlcoholVolume);
    List<WineDto> findWinesByResidualSugar(BigDecimal fromResidualSugar, BigDecimal toResidualSugar);
    List<WineDto> findWinesByAcidity(BigDecimal fromAcidity, BigDecimal toAcidity);
    List<WineDto> findWinesByGrapeSugarContent(BigDecimal fromGrapeSugarContent, BigDecimal toGrapeSugarContent);

    List<WineDto> findWinesBetweenYears(Year from, Year to);
    WineUpdateDto toWineUpdateDto(WineDto wineDto);

}
