package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.WineCreateDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.dto.WineUpdateDto;
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
        Wine wine = mappedWine(wineDto);

        wineService.createWine(wine);
        return wine.getId();
    }

    @Override
    public List<WineDto> findAllWines() {
        return beanMappingService.mapToCollection(wineService.findAllWines(), WineDto.class);
    }

    @Override
    public void updateWine(WineUpdateDto wineDto) {
        if (wineDto == null) {
            throw new IllegalArgumentException("WineDTO cannot be null");
        }
        wineService.updateWine(mappedWine(wineDto));
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

    @Override
    public WineUpdateDto toWineUpdateDto(WineDto wineDto) {
        WineUpdateDto wineUpdateDto = new WineUpdateDto();
        wineUpdateDto.setId(wineDto.getId());
        wineUpdateDto.setVintage(wineDto.getVintage().getValue());
        wineUpdateDto.setAcidity(wineDto.getAcidity());
        wineUpdateDto.setAlcoholVolume(wineDto.getAlcoholVolume());
        wineUpdateDto.setBatch(wineDto.getBatch());
        wineUpdateDto.setDescription(wineDto.getDescription());
        wineUpdateDto.setGrapeSugarContent(wineDto.getGrapeSugarContent());
        wineUpdateDto.setName(wineDto.getName());
        wineUpdateDto.setNotes(wineDto.getNotes());
        wineUpdateDto.setPredicate(wineDto.getPredicate());
        wineUpdateDto.setPredicateEquivalent(wineDto.getPredicateEquivalent());
        wineUpdateDto.setResidualSugar(wineDto.getResidualSugar());

        return wineUpdateDto;
    }

    private Wine mappedWine(WineCreateDto wineDto) {
        Wine wine = new Wine();
        wine.setVintage(Year.of(wineDto.getVintage()));
        wine.setAcidity(wineDto.getAcidity());
        wine.setAlcoholVolume(wineDto.getAlcoholVolume());
        wine.setBatch(wineDto.getBatch());
        wine.setDescription(wineDto.getDescription());
        wine.setGrapeSugarContent(wineDto.getGrapeSugarContent());
        wine.setName(wineDto.getName());
        wine.setNotes(wineDto.getNotes());
        wine.setPredicate(wineDto.getPredicate());
        wine.setPredicateEquivalent(wineDto.getPredicateEquivalent());
        wine.setResidualSugar(wineDto.getResidualSugar());

        return wine;
    }

    private Wine mappedWine(WineUpdateDto wineDto) {
        Wine wine = new Wine();
        wine.setVintage(Year.of(wineDto.getVintage()));
        wine.setAcidity(wineDto.getAcidity());
        wine.setAlcoholVolume(wineDto.getAlcoholVolume());
        wine.setBatch(wineDto.getBatch());
        wine.setDescription(wineDto.getDescription());
        wine.setGrapeSugarContent(wineDto.getGrapeSugarContent());
        wine.setName(wineDto.getName());
        wine.setNotes(wineDto.getNotes());
        wine.setPredicate(wineDto.getPredicate());
        wine.setPredicateEquivalent(wineDto.getPredicateEquivalent());
        wine.setResidualSugar(wineDto.getResidualSugar());

        return wine;
    }
}
