package cz.muni.fi.pa165.dao;

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
}