package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.WineList;
import org.joda.time.LocalDateTime;

import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.10.29
 */
public interface WineListDao {

    /**
     * create wine list in database
     * @param wineList
     */
    void createWineList(WineList wineList);

    /**
     * get wine list by id from database
     * @param id
     * @return wineList
     */
    WineList findWineListById(Long id);

    /**
     * Update wine list in database
     * @param wineList
     * @return wine list
     */
    void updateWineList(WineList wineList);

    /**
     * delete wineList from database
     * @param wineList
     */
    void deleteWineList(WineList wineList);

    /**
     * get all wine lists from database
     * @return list of wineLists
     */
    List<WineList> findAllWineLists();

    /**
     * find wine lists by date
     * @param date
     * @return list of wineLists
     */
    List<WineList> findWineListsByDate(LocalDateTime date);

    /**
     * Finds all wine lists by name in database
     * @param name of the wine list
     * @return list of all wine lists by given name
     */
    List<WineList> findWineListsByName(String name);

    /**
     * Finds wine list by marketing event in database
     * @param marketingEvent in wine list
     * @return wine list with given marketing event
     */
    List<WineList> findWineListByMarketingEvent(MarketingEvent marketingEvent);
}