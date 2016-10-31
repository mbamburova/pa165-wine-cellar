package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.WineList;
import org.joda.time.DateTime;

import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.10.29
 */
public interface WineListDao {

    void create(WineList wineList);
    WineList get(Long id);
    void update(WineList wineList);
    void delete(WineList wineList);
    List<WineList> getAll();
    List<WineList> findByDate(DateTime date);
}