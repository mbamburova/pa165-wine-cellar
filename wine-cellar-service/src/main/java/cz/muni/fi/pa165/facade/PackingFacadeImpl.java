package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.PackingCreateDto;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.PackingService;
import cz.muni.fi.pa165.service.WineService;
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
    private WineService wineService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public Long createPacking(PackingCreateDto packingDto) {
        if (packingDto == null){
            throw new IllegalArgumentException("PackingDTO cannot be null");
        }
        Packing packing = beanMappingService.mapTo(packingDto, Packing.class);
        packing.setWine(wineService.findWineById(packingDto.getWineId()));
        packingService.createPacking(packing);
        return packing.getId();
    }

    @Override
    public void updatePacking(PackingDto packingDto) {
        if (packingDto == null){
            throw new IllegalArgumentException("PackingDTO cannot be null");
        }
        Packing packing = beanMappingService.mapTo(packingDto, Packing.class);
        packing.setWine(wineService.findWineById(packingDto.getWine().getId()));
        packingService.updatePacking(packing);
    }

    @Override
    public void deletePacking(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Packing ID cannot be null");
        }
        Packing packing = packingService.findPackingById(id);
        packingService.deletePacking(packing);
    }

    @Override
    public PackingDto findPackingById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Packing ID cannot be null");
        }
        Packing packing = packingService.findPackingById(id);
        PackingDto packingDto = beanMappingService.mapTo(packing,PackingDto.class);
        packingDto.setWine(beanMappingService.mapTo(packing.getWine(), WineDto.class));
        
        return packingDto;
    }

    @Override
    public List<PackingDto> findAllPackings() {
        List<PackingDto> packings = beanMappingService.mapToCollection(packingService.findAllPackings(),
                PackingDto.class);
        for (PackingDto packing : packings){
            packing.setWine(beanMappingService.mapTo(packing.getWine(), WineDto.class));
        }
        return packings;
    }

    @Override
    public List<PackingDto> findPackingsByVolume(BigDecimal volume) {
        if (volume == null){
            throw new IllegalArgumentException("Volume cannot be null");
        }
        List<PackingDto> packings = beanMappingService.mapToCollection(packingService.findPackingsByVolume(volume), 
                PackingDto.class);
        for (PackingDto packing : packings){
            packing.setWine(beanMappingService.mapTo(packing.getWine(), WineDto.class));
        }
        return packings;
    }

    @Override
    public List<PackingDto> findPackingsByWine(WineDto wineDto) {
        if (wineDto == null){
            throw new IllegalArgumentException("WineDTO cannot be null");
        }
        Wine wine = beanMappingService.mapTo(wineDto, Wine.class);
        List<PackingDto> packings = beanMappingService.mapToCollection(packingService.findPackingsByWine(wine),
                PackingDto.class);
        for (PackingDto packing : packings){
            packing.setWine(beanMappingService.mapTo(packing.getWine(), WineDto.class));
        }
        return packings;
    }
}
