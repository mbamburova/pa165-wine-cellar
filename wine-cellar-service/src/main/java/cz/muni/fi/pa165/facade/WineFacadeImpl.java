package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.11.06
 */
public class WineFacadeImpl implements WineFacade {

    @Autowired
    private WineService wineService;

    @Override
    public Long createWine(WineDto wineDto) {
        Wine wine = new Wine();
        wine.setName(wineDto.getName());
        wine.setVintage(wineDto.getVintage());
        wine.setBatch(wineDto.getBatch());
        wine.setPredicate(wineDto.getPredicate());
        wine.setPredicateEquivalent(wineDto.getPredicateEquivalent());
        wine.setDescription(wineDto.getDescription());
        wine.setNotes(wineDto.getNotes());
        wine.setAlcoholVolume(wineDto.getAlcoholVolume());
        wine.setResidualSugar(wineDto.getResidualSugar());
        wine.setAcidity(wineDto.getAcidity());
        wine.setGrapeSugarContent(wineDto.getGrapeSugarContent());
        wineService.create(wine);
        return wine.getId();
    }

    @Override
    public WineDto findWineById(Long id) {
        if (wineService.get(id) != null) {
            return
        }
    }

    @Override
    public List<WineDto> findAllWines() {
        return null;
    }

    @Override
    public void updateWine(Long wineId) {

    }

    @Override
    public void updateWineName(Long wineId, String name) {

    }

    @Override
    public void updateWineVintage(Long wineId, Year vintage) {

    }

    @Override
    public void updateWineBatch(Long wineId, String batch) {

    }

    @Override
    public void updateWinePredicate(Long wineId, String predicate) {

    }

    @Override
    public void updateWinePredicateEquivalent(Long wineId, String predicateEquivalent) {

    }

    @Override
    public void updateWineDescription(Long wineId, String description) {

    }

    @Override
    public void updateWineNotes(Long wineId, String notes) {

    }

    @Override
    public void updateWineAlcoholVolume(Long wineId, BigDecimal alcoholVolume) {

    }

    @Override
    public void updateWineResidualSugar(Long wineId, BigDecimal residualSugar) {

    }

    @Override
    public void updateWineAcidity(Long wineId, BigDecimal acidity) {

    }

    @Override
    public void updateWineGrapeSugarContent(Long wineId, BigDecimal grapeSugarContent) {

    }

    @Override
    public void deleteWine(Long wineId) {

    }

    @Override
    public List<WineDto> findWinesByName(String name) {
        return null;
    }

    @Override
    public List<WineDto> findWinesByVintage(Year vintage) {
        return null;
    }

    @Override
    public WineDto findWineByBatch(String batch) {
        return null;
    }

    @Override
    public List<WineDto> findWinesByPredicate(String predicate) {
        return null;
    }

    @Override
    public List<WineDto> findWinesByPredicateEquivalent(String predicateEquivalent) {
        return null;
    }

    @Override
    public List<WineDto> findWinesByDescription(String description) {
        return null;
    }

    @Override
    public List<WineDto> findWinesByNotes(String notes) {
        return null;
    }

    @Override
    public List<WineDto> findWinesByAlcoholVolume(BigDecimal alcoholVolume) {
        return null;
    }

    @Override
    public List<WineDto> findWinesByResidualSugar(BigDecimal residualSugar) {
        return null;
    }

    @Override
    public List<WineDto> findWinesByAcidity(BigDecimal acidity) {
        return null;
    }

    @Override
    public List<WineDto> findWinesByGrapeSugarContent(BigDecimal grapeSugarContent) {
        return null;
    }

    @Override
    public List<WineDto> findWinesBetweenYears(Year from, Year to) {
        return null;
    }

    @Override
    public void addWinePackage(Long wine, Long packing) {

    }

    @Override
    public void removeWinePackage(Long wine, Long packing) {

    }
}
