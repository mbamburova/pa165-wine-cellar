package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Wine;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

/**
 * @author Silvia Borzová
 *         31/10/2016
 */

public interface WineDao {

    /**
     * creates new wine in database
     * @param wine
     */
    void createWine(Wine wine);

    /**
     * update given wine in database
     * @param wine
     */
    void updateWine(Wine wine);

    /**
     * delete given wine from database
     * @param wine
     */
    void deleteWine(Wine wine);

    /**
     *  get wine by given id
     * @param id
     * @return wine
     */
    Wine findWineById(Long id);

    /**
     *  get all wines from database
     * @return list of wines
     */
    List<Wine> findAllWines();

    /**
     *  get wines by given  names
     * @param name
     * @return list of wines
     */
    List<Wine> findWinesByName(String name);

    /**
     * get vines by given vintage
     * @param vintage
     * @return
     */
    List<Wine> findWinesByVintage(int vintage);

    /**
     *  get wines by given predicate
     * @param predicate
     * @return list of wines
     */
    List<Wine> findWinesByPredicate(String predicate);

    /**
     * get wines by given predicate equivalent
     * @param predicateEquivalent
     * @return list of wines
     */
    List<Wine> findWinesByPredicateEquivalent(String predicateEquivalent);

    /**
     * get wines by given alcoholVolume
     * @param from lower bound
     * @param to upper bound
     * @return list of wines
     */
    List<Wine> findWinesByAlcoholVolume(BigDecimal from, BigDecimal to);

    /**
     * get wines by given residualSugar
     * @param from lower bound
     * @param to upper bound
     * @return list of wines
     */
    List<Wine> findWinesByResidualSugar(BigDecimal from, BigDecimal to);

    /**
     * get wines by given acidity
     * @param from lower bound
     * @param to upper bound
     * @return list of wines
     */
    List<Wine> findWinesByAcidity(BigDecimal from, BigDecimal to);

    /**
     * get wines by given grapeSugarContent
     * @param from lower bound
     * @param to upper bound
     * @return list of wines
     */
    List<Wine> findWinesByGrapeSugarContent(BigDecimal from, BigDecimal to);

    /**
     * get wines by given years
     * @param from year
     * @param to year
     * @return list of wines
     */
    List<Wine> findWinesBetweenYears(int from, int to);

    /**
     * get wine by given batch
     * @param batch
     * @return wine
     */
    Wine findWineByBatch(String batch);

}
