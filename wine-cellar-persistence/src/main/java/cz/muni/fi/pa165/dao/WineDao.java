package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Wine;

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
    List<Wine> findByName(String name);

    /**
     * get vines by given vintage
     * @param name
     * @return
     */
    List<Wine> findByVintage(Year name);

    /**
     *  get wines by given predicate
     * @param predicate
     * @return list of wines
     */
    List<Wine> findByPredicate(String predicate);
}
