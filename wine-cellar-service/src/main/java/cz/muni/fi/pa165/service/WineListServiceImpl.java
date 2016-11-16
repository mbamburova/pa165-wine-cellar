package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.WineListDao;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.entity.WineList;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Michaela Bamburová on 08.11.2016.
 */
@Service
public class WineListServiceImpl implements WineListService {

    @Autowired
    private WineListDao wineListDao;

    @Override
    public void createWineList(WineList wineList) {
        wineListDao.create(wineList);
    }

    @Override
    public void deleteWineList(WineList wineList) {
        wineListDao.delete(wineList);
    }

    @Override
    public void updateWineList(WineList wineList) {
        wineListDao.update(wineList);
    }

    @Override
    public void addWine(WineList wineList, Wine wine) {
        wineList.addWine(wine);
    }

    @Override
    public void removeWine(WineList wineList, Wine wine) {
        wineList.removeWine(wine);
    }

    @Override
    public List<WineList> findAll() {
        return wineListDao.getAll();
    }

    @Override
    public WineList findWineListById(Long wineListId) {
        return wineListDao.get(wineListId);
    }

    @Override
    public WineList findByMarketingEvent(MarketingEvent marketingEvent) {
        return wineListDao.findByMarketingEvent(marketingEvent);
    }

    @Override
    public List<WineList> findByName(String name) {
        return wineListDao.findByName(name);
    }

    @Override
    public List<WineList> findByWine(Wine wine) {
        return null;
    }

    @Override
    public List<WineList> findByDate(LocalDateTime date) {
        return wineListDao.findByDate(date);
    }
}
