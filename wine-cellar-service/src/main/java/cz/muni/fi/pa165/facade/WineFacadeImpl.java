package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.11.06
 */
@Service
@Transactional
public class WineFacadeImpl implements WineFacade {

    @Autowired
    private WineService wineService;

    @Autowired
    private BeanMappingService beanMappingService;

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
        wineService.createWine(wine);
        return wine.getId();
    }

    @Override
    public List<WineDto> findAllWines() {
        return null;
    }

    @Override
    public void updateWine(WineDto wineDto) {
        Wine mappedWine = beanMappingService.mapTo(wineDto, Wine.class);
        wineService.updateWine(mappedWine);
    }

    @Override
    public WineDto findWineById(Long id) {
        if (wineService.findWineById(id) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapTo(wineService.findWineById(id), WineDto.class);
    }

    @Override
    public void deleteWine(WineDto wineDto) {
        Wine mappedWine = beanMappingService.mapTo(wineDto, Wine.class);
        wineService.deleteWine(mappedWine);
    }

    @Override
    public List<WineDto> findWinesByName(String name) {
        if (wineService.findWinesByName(name) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(wineService.findWinesByName(name), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesByVintage(Year vintage) {
        if (wineService.findWinesByVintage(vintage) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(wineService.findWinesByVintage(vintage), WineDto.class);
    }

    @Override
    public WineDto findWineByBatch(String batch) {
        if (wineService.findWineByBatch(batch) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapTo(wineService.findWineByBatch(batch), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesByPredicate(String predicate) {
        if (wineService.findWinesByPredicate(predicate) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(wineService.findWinesByPredicate(predicate), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesByPredicateEquivalent(String predicateEquivalent) {
        if (wineService.findWinesByPredicateEquivalent(predicateEquivalent) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(wineService.findWinesByPredicateEquivalent(predicateEquivalent), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesByAlcoholVolume(BigDecimal fromAlcoholVolume, BigDecimal toAlcoholVolume) {
        if (wineService.findWinesByAlcoholVolume(fromAlcoholVolume, toAlcoholVolume) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(wineService.findWinesByAlcoholVolume(fromAlcoholVolume, toAlcoholVolume), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesByResidualSugar(BigDecimal fromResidualSugar, BigDecimal toResidualSugar) {
        if (wineService.findWinesByResidualSugar(fromResidualSugar, toResidualSugar) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(wineService.findWinesByResidualSugar(fromResidualSugar, toResidualSugar), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesByAcidity(BigDecimal fromAcidity, BigDecimal toAcidity) {
        if (wineService.findWinesByAcidity(fromAcidity, toAcidity) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(wineService.findWinesByAcidity(fromAcidity, toAcidity), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesByGrapeSugarContent(BigDecimal fromGrapeSugarContent, BigDecimal toGrapeSugarContent) {
        if (wineService.findWinesByGrapeSugarContent(fromGrapeSugarContent, toGrapeSugarContent) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(wineService.findWinesByGrapeSugarContent(fromGrapeSugarContent, toGrapeSugarContent), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesBetweenYears(Year from, Year to) {
        if (wineService.findWinesBetweenYears(from, to) == null) {
            throw new NoResultException();
        }
        return beanMappingService.mapToCollection(wineService.findWinesBetweenYears(from, to), WineDto.class);
    }
}
