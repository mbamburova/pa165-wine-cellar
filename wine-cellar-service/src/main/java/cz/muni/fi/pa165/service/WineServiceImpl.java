package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PackingDao;
import cz.muni.fi.pa165.dao.PriceDao;
import cz.muni.fi.pa165.dao.WineDao;
import cz.muni.fi.pa165.entity.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
@Service
@Transactional
public class WineServiceImpl implements WineService {

    @Autowired
    private WineDao wineDao;
    private PackingDao packingDao;
    private PriceDao priceDao;

    @Override
    public void createWine(Wine wine) {
        wineDao.createWine(wine);
    }

    @Override
    public void deleteWine(Wine wine) {
        wineDao.deleteWine(wine);
    }

    @Override
    public void updateWine(Wine wine) {
        wineDao.updateWine(wine);
    }

    @Override
    public List<Wine> findAllWines() {
        return wineDao.findAllWines();
    }

    @Override
    public Wine findWineById(Long id) {
        return wineDao.findWineById(id);
    }

    @Override
    public List<Wine> findWinesByName(String name) {
        return wineDao.findWinesByName(name);
    }

    @Override
    public List<Wine> findWinesByVintage(Year vintage) {
        return wineDao.findWinesByVintage(vintage);
    }

    @Override
    public Wine findWineByBatch(String batch) {
        return wineDao.findWineByBatch(batch);
    }

    @Override
    public List<Wine> findWinesByPredicate(String predicate) {
        return wineDao.findWinesByPredicate(predicate);
    }

    @Override
    public List<Wine> findWinesByPredicateEquivalent(String predicateEquivalent) {
        return wineDao.findWinesByPredicateEquivalent(predicateEquivalent);
    }

    @Override
    public List<Wine> findWinesByAlcoholVolume(BigDecimal minAlcoholVolume, BigDecimal maxAlcoholVolume) {
        return wineDao.findWinesByAlcoholVolume(minAlcoholVolume, maxAlcoholVolume);
    }

    @Override
    public List<Wine> findWinesByResidualSugar(BigDecimal minResidualSugar, BigDecimal maxResidualSugar) {
        return wineDao.findWinesByResidualSugar(minResidualSugar, maxResidualSugar);
    }

    @Override
    public List<Wine> findWinesByAcidity(BigDecimal minAcidity, BigDecimal maxAcidity) {
        return wineDao.findWinesByAcidity(minAcidity, maxAcidity);
    }

    @Override
    public List<Wine> findWinesByGrapeSugarContent(BigDecimal minGrapeSugarContent, BigDecimal maxGrapeSugarContent) {
        return wineDao.findWinesByGrapeSugarContent(minGrapeSugarContent, maxGrapeSugarContent);
    }

    @Override
    public List<Wine> findWinesBetweenYears(Year from, Year to) {
        return wineDao.findWinesBetweenYears(from, to);
    }
}
