package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PackingDao;
import cz.muni.fi.pa165.dao.PriceDao;
import cz.muni.fi.pa165.dao.WineDao;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.entity.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
@Service
public class WineServiceImpl implements WineService {

    @Autowired
    private WineDao wineDao;
    private PackingDao packingDao;
    private PriceDao priceDao;

    @Override
    public void create(Wine wine) {
        wineDao.createWine(wine);
    }

    @Override
    public void delete(Wine wine) {
        for(Packing packing : wine.getPackings()) {
            for(Price price : packing.getPrices()) {
                priceDao.deletePrice(price);
            }
            packingDao.deletePacking(packing);
        }
        wineDao.deleteWine(wine);
    }

    @Override
    public void update(Wine wine) {
        wineDao.updateWine(wine);
    }

    @Override
    public void updateName(Wine wine, String name) {
        wine.setName(name);
        wineDao.updateWine(wine);
    }

    @Override
    public void updateVintage(Wine wine, Year vintage) {
        wine.setVintage(vintage);
        wineDao.updateWine(wine);
    }

    @Override
    public void updateBatch(Wine wine, String batch) {
        wine.setBatch(batch);
        wineDao.updateWine(wine);
    }

    @Override
    public void updatePredicate(Wine wine, String predicate) {
        wine.setPredicate(predicate);
        wineDao.updateWine(wine);
    }

    @Override
    public void updatePredicateEquivalent(Wine wine, String predicateEquivalent) {
        wine.setPredicateEquivalent(predicateEquivalent);
        wineDao.updateWine(wine);
    }

    @Override
    public void updateDescription(Wine wine, String description) {
        wine.setDescription(description);
        wineDao.updateWine(wine);
    }

    @Override
    public void updateNotes(Wine wine, String notes) {
        wine.setNotes(notes);
        wineDao.updateWine(wine);
    }

    @Override
    public void updateAlcoholVolume(Wine wine, BigDecimal alcoholVolume) {
        wine.setAlcoholVolume(alcoholVolume);
        wineDao.updateWine(wine);
    }

    @Override
    public void updateResidualSugar(Wine wine, BigDecimal residualSugar) {
        wine.setResidualSugar(residualSugar);
        wineDao.updateWine(wine);
    }

    @Override
    public void updateAcidity(Wine wine, BigDecimal acidity) {
        wine.setAcidity(acidity);
        wineDao.updateWine(wine);
    }

    @Override
    public void updateGrapeSugarContent(Wine wine, BigDecimal grapeSugarContent) {
        wine.setGrapeSugarContent(grapeSugarContent);
        wineDao.updateWine(wine);
    }

    @Override
    public List<Wine> findAll() {
        return wineDao.findAllWines();
    }

    @Override
    public Wine get(Long id) {
        return wineDao.findWineById(id);
    }

    @Override
    public List<Wine> findByName(String name) {
        return wineDao.findByName(name);
    }

    @Override
    public List<Wine> findByVintage(Year vintage) {
        return wineDao.findByVintage(vintage);
    }

    @Override
    public Wine findByBatch(String batch) {
        return wineDao.findByBatch(batch);
    }

    @Override
    public List<Wine> findByPredicate(String predicate) {
        return wineDao.findByPredicate(predicate);
    }

    @Override
    public List<Wine> findByPredicateEquivalent(String predicateEquivalent) {
        return wineDao.findByPredicateEquivalent(predicateEquivalent);
    }

    @Override
    public List<Wine> findByAlcoholVolume(BigDecimal minAlcoholVolume, BigDecimal maxAlcoholVolume) {
        return wineDao.findByAlcoholVolume(alcoholVolume);
    }

    @Override
    public List<Wine> findByResidualSugar(BigDecimal minResidualSugar, BigDecimal maxResidualSugar) {
        return wineDao.findByResidualSugar(residualSugar);
    }

    @Override
    public List<Wine> findByAcidity(BigDecimal minAcidity, BigDecimal maxAcidity) {
        return wineDao.findByAcidity(acidity);
    }

    @Override
    public List<Wine> findByGrapeSugarContent(BigDecimal minGrapeSugarContent, BigDecimal maxGrapeSugarContent) {
        return wineDao.findByGrapeSugarContent(grapeSugarContent);
    }

    @Override
    public List<Wine> findAllWinesFromYears(Year from, Year to) {
        return findAll().stream().filter(wine -> from.isBefore(wine.getVintage()) &&
                to.isAfter(wine.getVintage())).collect(Collectors.toList());
    }
}
