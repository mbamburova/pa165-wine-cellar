package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.entity.WineList;
import org.joda.time.LocalDateTime;

import java.util.List;

/**
 * @author Michaela Bamburová on 08.11.2016.
 */
public interface WineListService {

    void createWineList(WineList wineList);
    void updateWineList(WineList wineList);
    void deleteWineList(WineList wineList);

    void addWine(WineList wineList, Wine wine);
    void removeWine(WineList wineList, Wine wine);

    List<WineList> findAllWineLists();
    WineList findWineListById(Long wineListId);
    WineList findWineListByMarketingEvent(MarketingEvent marketingEvent);
    List<WineList> findWineListByName(String name);
    List<WineList> findWineListByDate(LocalDateTime date);
}
