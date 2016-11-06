package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Wine;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public interface WineService {

    void create(Wine wine);
    void delete(Wine wine);
    void update(Wine wine);
    void updateName(Wine wine, String name);
    void updateVintage(Wine wine, Year vintage);
    void updateBatch(Wine wine, String batch);
    void updatePredicate(Wine wine, String predicate);
    void updatePredicateEquivalent(Wine wine, String predicateEquivalent);
    void updateDescription(Wine wine, String description);
    void updateNotes(Wine wine, String notes);
    void updateAlcoholVolume(Wine wine, BigDecimal alcoholVolume);
    void updateResidualSugar(Wine wine, BigDecimal residualSugar);
    void updateAcidity(Wine wine, BigDecimal acidity);
    void updateGrapeSugarContent(Wine wine, BigDecimal grapeSugarContent);

    List<Wine> findAll();
    Wine get(Long id);
    List<Wine> findByName(String name);
    List<Wine> findByVintage(Year vintage);
    Wine findByBatch(String batch);
    List<Wine> findByPredicate(String predicate);
    List<Wine> findByPredicateEquivalent(String predicateEquivalent);
    List<Wine> findByDescription(String description);
    List<Wine> findByNotes(String notes);
    List<Wine> findByAlcoholVolume(BigDecimal alcoholVolume);
    List<Wine> findByResidualSugar(BigDecimal residualSugar);
    List<Wine> findByAcidity(BigDecimal acidity);
    List<Wine> findByGrapeSugarContent(BigDecimal grapeSugarContent);

    List<Wine> findAllWinesFromYears(Year from, Year to);

    void addPackage(Wine wine, Packing packing);
    void removePackage(Wine wine, Packing packing);
}
