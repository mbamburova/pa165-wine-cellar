package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Year;

/**
 * @author MarekScholtz
 * @version 2016.11.06
 */
public class WineFacadeImpl implements WineFacade {

    @Autowired
    private WineService wineService;

    @Override
    public Long create(WineDto wineDto) {
        Wine wine = new Wine();
        wine.setName(wineDto.getName());
        wine.setVintage(wineDto.getVintage);
        wine.setBatch(wineDto.getBatch);
        wine.setPredicate(wineDto.getPredicate);
        wine.setPredicateEquivalent(wineDto.getPredicateEquivalent);
        wine.setDescription(wineDto.getDescription);
        wine.setNotes(wineDto.getNotes);
        wine.setAlcoholVolume(wineDto.getAlcoholVolume);
        wine.setResidualSugar(wineDto.getResidualSugar);
        wine.setAcidity(wineDto.getAcidity);
        wine.setGrapeSugarContent(wineDto.getGrapeSugarContent);
        wineService.create(wine);
        return wine.getId();
    }

    @Override
    public WineDto get(Long id) {
        if (wineService.get(id) != null) {
            return
        }
    }

    @Override
    public List<WineDto> getAll() {
        return null;
    }

    @Override
    public void update(Long wineId) {

    }

    @Override
    public void updateName(Long wineId, String name) {

    }

    @Override
    public void updateVintage(Long wineId, Year vintage) {

    }

    @Override
    public void updateBatch(Long wineId, String batch) {

    }

    @Override
    public void updatePredicate(Long wineId, String predicate) {

    }

    @Override
    public void updatePredicateEquivalent(Long wineId, String predicateEquivalent) {

    }

    @Override
    public void updateDescription(Long wineId, String description) {

    }

    @Override
    public void updateNotes(Long wineId, String notes) {

    }

    @Override
    public void updateAlcoholVolume(Long wineId, BigDecimal alcoholVolume) {

    }

    @Override
    public void updateResidualSugar(Long wineId, BigDecimal residualSugar) {

    }

    @Override
    public void updateAcidity(Long wineId, BigDecimal acidity) {

    }

    @Override
    public void updateGrapeSugarContent(Long wineId, BigDecimal grapeSugarContent) {

    }

    @Override
    public void delete(Long wineId) {

    }

    @Override
    public List<WineDto> findByName(String name) {
        return null;
    }

    @Override
    public List<WineDto> findByVintage(Year vintage) {
        return null;
    }

    @Override
    public WineDto findByBatch(String batch) {
        return null;
    }

    @Override
    public List<WineDto> findByPredicate(String predicate) {
        return null;
    }

    @Override
    public List<WineDto> findByPredicateEquivalent(String predicateEquivalent) {
        return null;
    }

    @Override
    public List<WineDto> findByDescription(String description) {
        return null;
    }

    @Override
    public List<WineDto> findByNotes(String notes) {
        return null;
    }

    @Override
    public List<WineDto> findByAlcoholVolume(BigDecimal alcoholVolume) {
        return null;
    }

    @Override
    public List<WineDto> findByResidualSugar(BigDecimal residualSugar) {
        return null;
    }

    @Override
    public List<WineDto> findByAcidity(BigDecimal acidity) {
        return null;
    }

    @Override
    public List<WineDto> findByGrapeSugarContent(BigDecimal grapeSugarContent) {
        return null;
    }

    @Override
    public List<WineDto> findBetweenYears(Year from, Year to) {
        return null;
    }

    @Override
    public void addPackage(Long wine, Long packing) {

    }

    @Override
    public void removePackage(Long wine, Long packing) {

    }
}
