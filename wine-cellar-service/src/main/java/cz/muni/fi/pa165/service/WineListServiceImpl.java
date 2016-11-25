package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.WineListDao;
import cz.muni.fi.pa165.entity.MarketingEvent;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.entity.WineList;
import cz.muni.fi.pa165.exception.WineCellarDataAccessException;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Michaela Bamburová on 08.11.2016.
 */
@Service
@Transactional
public class WineListServiceImpl implements WineListService {

    @Autowired
    private WineListDao wineListDao;

    @Override
    public void createWineList(WineList wineList) {
        try {
            wineListDao.createWineList(wineList);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("Cannot create wineList", e);
        }
    }

    @Override
    public void deleteWineList(WineList wineList) {
        try {
            wineListDao.deleteWineList(wineList);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("Cannot delete wineList", e);
        }
    }

    @Override
    public void updateWineList(WineList wineList) {
        try {
            wineListDao.updateWineList(wineList);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("Cannot update wineList");
        }
    }

    @Override
    public void addWine(WineList wineList, Wine wine) {
        try {
            wineList.addWine(wine);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("Cannot add wine to wineList", e);
        }
    }

    @Override
    public void removeWine(WineList wineList, Wine wine) {
        try {
            wineList.removeWine(wine);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("Cannot remove wine from wineList", e);
        }
    }

    @Override
    public List<WineList> findAllWineLists() {
        try {
            return wineListDao.findAllWineLists();
        } catch (Exception e) {
            throw new WineCellarDataAccessException("Cannot find all wineLists", e);
        }
    }

    @Override
    public WineList findWineListById(Long wineListId) {
        try {
            return wineListDao.findWineListById(wineListId);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("Cannot find wineList by id", e);
        }
    }

    @Override
    public WineList findWineListByMarketingEvent(MarketingEvent marketingEvent) {
        try {
            return wineListDao.findWineListByMarketingEvent(marketingEvent);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("Cannot find wineList by marketing event", e);
        }
    }

    @Override
    public List<WineList> findWineListByName(String name) {
        try {
            return wineListDao.findWineListsByName(name);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("Cannot find wineList by name", e);
        }
    }

    @Override
    public List<WineList> findWineListByDate(LocalDateTime date) {
         try {
             return wineListDao.findWineListsByDate(date);
         } catch (Exception e) {
             throw new WineCellarDataAccessException("Cannot find wine list by date", e);
         }
    }
}
