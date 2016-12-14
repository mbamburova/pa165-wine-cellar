package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.PackingCreateDto;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.PackingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
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
    private BeanMappingService beanMappingService;

    @Override
    public Long createPacking(PackingCreateDto packingDto) {
        Packing packing = beanMappingService.mapTo(packingDto, Packing.class);
        packingService.createPacking(packing);
        return packing.getId();
    }

    @Override
    public void updatePacking(PackingDto packingDto) {
        Packing packing = beanMappingService.mapTo(packingDto, Packing.class);
        packingService.updatePacking(packing);
    }

    @Override
    public void deletePacking(Long packingId) {
        Packing packing = packingService.findPackingById(packingId);
        packingService.deletePacking(packing);
    }

    @Override
    public PackingDto findPackingById(Long id) {
        Packing packing = packingService.findPackingById(id);
        return (packing == null) ? null : beanMappingService.mapToEnforceID(packing,PackingDto.class);
    }

    @Override
    public List<PackingDto> findAllPackings() {
        return beanMappingService.mapToCollectionEnforceID(packingService.findAllPackings(),
                PackingDto.class);
    }

    @Override
    public List<PackingDto> findPackingsByVolume(BigDecimal volume) {
        return beanMappingService.mapToCollectionEnforceID(packingService.findPackingsByVolume(volume),
                PackingDto.class);
    }

    @Override
    public List<PackingDto> findPackingsByWine(WineDto wineDto) {
        Wine wine = beanMappingService.mapTo(wineDto, Wine.class);

        return beanMappingService.mapToCollectionEnforceID(packingService.findPackingsByWine(wine),
                PackingDto.class);
    }
}
