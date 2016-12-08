package cz.muni.fi.pa165.facade;

import java.math.BigDecimal;
import java.util.List;
import cz.muni.fi.pa165.dto.PackingCreateDto;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

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
    public void createPacking(PackingCreateDto packingCreateDto) {
        Packing packing = beanMappingService.mapTo(packingCreateDto, Packing.class);
        packing.setWine(wineService.findWineById(packingCreateDto.getWineId()));
        packingService.createPacking(packing);
    }

    @Override
    public void updatePacking(PackingCreateDto packingCreateDto) {
        Packing packing = beanMappingService.mapTo(packingCreateDto, Packing.class);
        packing.setWine(wineService.findWineById(packingCreateDto.getWineId()));
        packingService.updatePacking(packing);
    }

    @Override
    public void deletePacking(PackingDto packingDto) {
        packingService.deletePacking(new Packing(packingDto.getId()));
    }

//    @Override
//    public boolean isPackingValid(PackingDto p) {
//        Packing packing = beanMappingService.mapTo(p, Packing.class);
//        return packingService.isPackingValid(packing);
//    }

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
