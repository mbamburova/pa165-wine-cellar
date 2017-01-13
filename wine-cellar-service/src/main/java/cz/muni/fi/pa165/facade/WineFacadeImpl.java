package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.wine.WineCreateDto;
import cz.muni.fi.pa165.dto.wine.WineDto;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.WineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
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

    @Inject
    private WineService wineService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public Long createWine(WineCreateDto wineDto) {
        if (wineDto == null) {
            throw new IllegalArgumentException("WineDTO cannot be null");
        }
        Wine wine = beanMappingService.mapTo(wineDto, Wine.class);

        wineService.createWine(wine);
        return wine.getId();
    }

    @Override
    public List<WineDto> findAllWines() {
        return beanMappingService.mapToCollection(wineService.findAllWines(), WineDto.class);
    }

    @Override
    public void updateWine(WineDto wineDto) {
        if (wineDto == null) {
            throw new IllegalArgumentException("WineDTO cannot be null");
        }
        Wine wine = beanMappingService.mapTo(wineDto, Wine.class);
        wineService.updateWine(wine);
    }

    @Override
    public WineDto findWineById(Long id) {
        if (wineService.findWineById(id) == null) {
            throw new IllegalArgumentException("Wine ID cannot be null");
        }
        return beanMappingService.mapTo(wineService.findWineById(id), WineDto.class);
    }

    @Override
    public void deleteWine(Long wineId) {
        Wine wine = wineService.findWineById(wineId);
        wineService.deleteWine(wine);
    }

    @Override
    public List<WineDto> findWinesByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Wine name cannot be null");
        }
        return beanMappingService.mapToCollection(wineService.findWinesByName(name), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesByVintage(Year vintage) {
        if (vintage == null) {
            throw new IllegalArgumentException("Wine vintage cannot be null");
        }
        return beanMappingService.mapToCollection(wineService.findWinesByVintage(vintage), WineDto.class);
    }

    @Override
    public WineDto findWineByBatch(String batch) {
        if (batch == null) {
            throw new IllegalArgumentException("Wine batch cannot be null");
        }
        return beanMappingService.mapTo(wineService.findWineByBatch(batch), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesByPredicate(String predicate) {
        if (predicate == null) {
            throw new IllegalArgumentException("Wine predicate cannot be null");
        }
        return beanMappingService.mapToCollection(wineService.findWinesByPredicate(predicate), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesByPredicateEquivalent(String predicateEquivalent) {
        if (predicateEquivalent == null) {
            throw new IllegalArgumentException("Wine predicate equivalent cannot be null");
        }
        return beanMappingService.mapToCollection(wineService.findWinesByPredicateEquivalent(predicateEquivalent), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesByAlcoholVolume(BigDecimal fromAlcoholVolume, BigDecimal toAlcoholVolume) {
        if (fromAlcoholVolume == null) {
            throw new IllegalArgumentException("Wine fromAlcoholVolume cannot be null");
        }
        if (toAlcoholVolume == null) {
            throw new IllegalArgumentException("Wine toAlcoholVolume cannot be null");
        }
        return beanMappingService.mapToCollection(wineService.findWinesByAlcoholVolume(fromAlcoholVolume, toAlcoholVolume), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesByResidualSugar(BigDecimal fromResidualSugar, BigDecimal toResidualSugar) {
        if (fromResidualSugar == null) {
            throw new IllegalArgumentException("Wine fromResidualSugar cannot be null");
        }
        if (toResidualSugar == null) {
            throw new IllegalArgumentException("Wine toResidualSugar cannot be null");
        }
        return beanMappingService.mapToCollection(wineService.findWinesByResidualSugar(fromResidualSugar, toResidualSugar), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesByAcidity(BigDecimal fromAcidity, BigDecimal toAcidity) {
        if (fromAcidity == null) {
            throw new IllegalArgumentException("Wine fromAcidity cannot be null");
        }
        if (toAcidity == null) {
            throw new IllegalArgumentException("Wine toAcidity cannot be null");
        }
        return beanMappingService.mapToCollection(wineService.findWinesByAcidity(fromAcidity, toAcidity), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesByGrapeSugarContent(BigDecimal fromGrapeSugarContent, BigDecimal toGrapeSugarContent) {
        if (fromGrapeSugarContent == null) {
            throw new IllegalArgumentException("Wine fromGrapeSugarContent cannot be null");
        }
        if (toGrapeSugarContent == null) {
            throw new IllegalArgumentException("Wine toGrapeSugarContent cannot be null");
        }
        return beanMappingService.mapToCollection(wineService.findWinesByGrapeSugarContent(fromGrapeSugarContent, toGrapeSugarContent), WineDto.class);
    }

    @Override
    public List<WineDto> findWinesBetweenYears(Year from, Year to) {
        if (from == null) {
            throw new IllegalArgumentException("Wine fromYear cannot be null");
        }
        if (to == null) {
            throw new IllegalArgumentException("Wine toYear cannot be null");
        }
        return beanMappingService.mapToCollection(wineService.findWinesBetweenYears(from, to), WineDto.class);
    }

}
