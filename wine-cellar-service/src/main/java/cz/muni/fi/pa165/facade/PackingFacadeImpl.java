package cz.muni.fi.pa165.facade;

import java.math.BigDecimal;
import java.util.List;
import cz.muni.fi.pa165.dto.PackingCreateDto;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.PackingService;
import cz.muni.fi.pa165.service.WineService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

@Service
@Transactional
public class PackingFacadeImpl implements PackingFacade {

    @Autowired
    private PackingService packingService;

    @Autowired
    private WineService wineService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public void createPacking(PackingCreateDto p) {
        //packingService.create(convertDto(p));

        Packing packing = beanMappingService.mapTo(p, Packing.class);
        packing.setWine(wineService.findWineById(p.getWineId()));
        packingService.createPacking(packing);
    }

    @Override
    public void updatePacking(PackingCreateDto p) {
        Packing packing = beanMappingService.mapTo(p, Packing.class);
        packingService.updatePacking(packing);
    }

    @Override
    public void deletePacking(PackingDto p) {
        packingService.deletePacking(new Packing(p.getId()));
    }

    @Override
    public boolean isPackingValid(PackingDto p) {
        Packing packing = beanMappingService.mapTo(p, Packing.class);
        return packingService.isPackingValid(packing);
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
    public List<PackingDto> findPackingByVolume(BigDecimal volume) {
        return beanMappingService.mapToCollectionEnforceID(packingService.findPackingsByVolume(volume),
                PackingDto.class);
    }

    @Override
    public List<PackingDto> findPackingByWine(WineDto wineDto) {
        Wine wine = beanMappingService.mapTo(wineDto, Wine.class);

        return beanMappingService.mapToCollectionEnforceID(packingService.findPackingsByWine(wine),
                PackingDto.class);
    }


    private Packing convertDto(PackingCreateDto dto) {
        Packing p = new Packing();
        //p.setWine(wineService.get(dto.getId())); //alebo convertWineToDto ?
        p.setVolume(dto.getVolume());
        p.setValidFrom(dto.getValidFrom());
        p.setValidTo(dto.getValidTo());
        return p;
    }
}
