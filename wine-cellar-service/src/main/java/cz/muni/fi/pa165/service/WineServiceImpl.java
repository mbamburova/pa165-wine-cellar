package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PackingDao;
import cz.muni.fi.pa165.dao.PriceDao;
import cz.muni.fi.pa165.dao.WineDao;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.exception.WineCellarDataAccessException;
import org.dozer.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import static sun.java2d.cmm.ColorTransform.In;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
@Service
public class WineServiceImpl implements WineService {

    @Inject
    private WineDao wineDao;
    private PackingDao packingDao;
    private PriceDao priceDao;

    @Override
    public void createWine(Wine wine) {
        try {
            wineDao.createWine(wine);
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot create wine", e);
        }
    }

    @Override
    public void deleteWine(Wine wine) {
        try {
        wineDao.deleteWine(wine);
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot delete wine", e);
        }
    }

    @Override
    public void updateWine(Wine wine) {
        try {
            wineDao.updateWine(wine);
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot update wine", e);
        }
    }

    @Override
    public List<Wine> findAllWines() {
        try {
            return wineDao.findAllWines();
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot find all wines", e);
        }
    }

    @Override
    public Wine findWineById(Long id) {
        try {
            return wineDao.findWineById(id);
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot find wine by id", e);
        }
    }

    @Override
    public List<Wine> findWinesByName(String name) {
        try {
            return wineDao.findWinesByName(name);
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot find wines by name", e);
        }
    }

    @Override
    public List<Wine> findWinesByVintage(int vintage) {
        try {
            return wineDao.findWinesByVintage(vintage);
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot find wines by vintage", e);
        }
    }

    @Override
    public Wine findWineByBatch(String batch) {
        try {
            return wineDao.findWineByBatch(batch);
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot find wine by batch", e);
        }
    }

    @Override
    public List<Wine> findWinesByPredicate(String predicate) {
        try {
            return wineDao.findWinesByPredicate(predicate);
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot find wines by predicate", e);
        }
    }

    @Override
    public List<Wine> findWinesByPredicateEquivalent(String predicateEquivalent) {
        try {
            return wineDao.findWinesByPredicateEquivalent(predicateEquivalent);
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot find wines by predicate equivalent", e);
        }
    }

    @Override
    public List<Wine> findWinesByAlcoholVolume(BigDecimal minAlcoholVolume, BigDecimal maxAlcoholVolume) {
        try {
            return wineDao.findWinesByAlcoholVolume(minAlcoholVolume, maxAlcoholVolume);
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot find wines by alcohol volume", e);
        }
    }

    @Override
    public List<Wine> findWinesByResidualSugar(BigDecimal minResidualSugar, BigDecimal maxResidualSugar) {
        try {
            return wineDao.findWinesByResidualSugar(minResidualSugar, maxResidualSugar);
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot find wines by residual sugar", e);
        }
    }

    @Override
    public List<Wine> findWinesByAcidity(BigDecimal minAcidity, BigDecimal maxAcidity) {
        try {
            return wineDao.findWinesByAcidity(minAcidity, maxAcidity);
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot find wines by acidity", e);
        }
    }

    @Override
    public List<Wine> findWinesByGrapeSugarContent(BigDecimal minGrapeSugarContent, BigDecimal maxGrapeSugarContent) {
        try {
            return wineDao.findWinesByGrapeSugarContent(minGrapeSugarContent, maxGrapeSugarContent);
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot find wines by grape sugar content", e);
        }
    }

    @Override
    public List<Wine> findWinesBetweenYears(int from, int to) {
        try {
            return wineDao.findWinesBetweenYears(from, to);
        } catch(Exception e) {
            throw new WineCellarDataAccessException("Cannot find wines", e);
        }
    }
}
