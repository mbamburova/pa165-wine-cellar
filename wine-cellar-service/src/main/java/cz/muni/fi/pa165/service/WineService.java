package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Wine;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public interface WineService {

    void createWine(Wine wine);
    void deleteWine(Wine wine);
    void updateWine(Wine wine);

    List<Wine> findAllWines();
    Wine findWineById(Long id);
    List<Wine> findWinesByName(String name);
    List<Wine> findWinesByVintage(Year vintage);
    Wine findWineByBatch(String batch);
    List<Wine> findWinesByPredicate(String predicate);
    List<Wine> findWinesByPredicateEquivalent(String predicateEquivalent);
    List<Wine> findWinesByAlcoholVolume(BigDecimal minAlcoholVolume, BigDecimal maxAlcoholVolume);
    List<Wine> findWinesByResidualSugar(BigDecimal minResidualSugar, BigDecimal maxResidualSugar);
    List<Wine> findWinesByAcidity(BigDecimal minAcidity, BigDecimal maxAcidity);
    List<Wine> findWinesByGrapeSugarContent(BigDecimal minGrapeSugarContent, BigDecimal maxGrapeSugarContent);
    List<Wine> findWinesBetweenYears(Year from, Year to);

}
