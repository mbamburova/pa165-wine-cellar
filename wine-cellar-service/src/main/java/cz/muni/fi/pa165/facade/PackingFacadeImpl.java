package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.packing.PackingCreateDto;
import cz.muni.fi.pa165.dto.packing.PackingDto;
import cz.muni.fi.pa165.dto.wine.WineDto;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.PackingService;
import cz.muni.fi.pa165.service.WineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

@Service
@Transactional
public class PackingFacadeImpl implements PackingFacade {

    @Inject
    private PackingService packingService;

    @Inject
    private WineService wineService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public Long createPacking(PackingCreateDto packingDto) {
        if (packingDto == null) {
            throw new IllegalArgumentException("PackingDTO cannot be null");
        }
        Packing packing = beanMappingService.mapTo(packingDto, Packing.class);
        packing.setWine(wineService.findWineById(packingDto.getWineId()));
        packingService.createPacking(packing);
        return packing.getId();
    }

    @Override
    public void updatePacking(PackingDto packingDto) {
        if (packingDto == null) {
            throw new IllegalArgumentException("PackingDTO cannot be null");
        }
        Packing packing = beanMappingService.mapTo(packingDto, Packing.class);
        packing.setWine(wineService.findWineById(packingDto.getWine().getId()));
        packingService.updatePacking(packing);
    }

    @Override
    public void deletePacking(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Packing ID cannot be null");
        }
            packingService.deletePacking(packingService.findPackingById(id));
    }

    @Override
    public PackingDto findPackingById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Packing ID cannot be null");
        }
        Packing packing = packingService.findPackingById(id);
        return beanMappingService.mapTo(packing, PackingDto.class);
    }

    @Override
    public List<PackingDto> findAllPackings() {
        return beanMappingService.mapToCollection(packingService.findAllPackings(), PackingDto.class);
    }

    @Override
    public List<PackingDto> findPackingsByVolume(BigDecimal volume) {
        if (volume == null) {
            throw new IllegalArgumentException("Volume cannot be null");
        }
        return beanMappingService.mapToCollection(packingService.findPackingsByVolume(volume), PackingDto.class);
    }

    @Override
    public List<PackingDto> findPackingsByWine(WineDto wineDto) {
        if (wineDto == null) {
            throw new IllegalArgumentException("WineDTO cannot be null");
        }
        Wine wine = beanMappingService.mapTo(wineDto, Wine.class);
        return beanMappingService.mapToCollection(packingService.findPackingsByWine(wine), PackingDto.class);
    }
}
