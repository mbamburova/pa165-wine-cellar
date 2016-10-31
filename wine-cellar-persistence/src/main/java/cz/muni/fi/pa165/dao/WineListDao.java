package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.WineList;
import org.joda.time.DateTime;

import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.10.29
 */
public interface WineListDao {

    /**
     * create wineList in database
     * @param wineList
     */
    void create(WineList wineList);

    /**
     * get wineList by id from database
     * @param id
     * @return wineList
     */
    WineList get(Long id);

    /**
     * update wineList in database
     * @param wineList
     */
    void update(WineList wineList);

    /**
     * delete wineList from database
     * @param wineList
     */
    void delete(WineList wineList);

    /**
     * get all wineLists from database
     * @return list of wineLists
     */
    List<WineList> getAll();

    /**
     * find wineLists by date
     * @param date
     * @return list of wineLists
     */
    List<WineList> findByDate(DateTime date);
}