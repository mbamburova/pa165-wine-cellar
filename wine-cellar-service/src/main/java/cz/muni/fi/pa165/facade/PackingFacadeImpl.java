package cz.muni.fi.pa165.facade;

import java.math.BigDecimal;
import java.util.List;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.PackingService;
import cz.muni.fi.pa165.service.WineService;
import org.joda.time.DateTime;
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
    public void createPacking(PackingDto p) {
        //packingService.create(convertDto(p));

        //Packing packing = beanMappingService.mapTo(p, Packing.class);

    }

    @Override
    public void updatePacking(PackingDto p) {

    }

    @Override
    public void deletePacking(PackingDto p) {
        packingService.delete(new Packing(p.getId()));
    }

    @Override
    public PackingDto findPackingById(Long id) {
        Packing packing = packingService.get(id);
        return (packing == null) ? null : beanMappingService.mapToEnforceID(packing,PackingDto.class);
    }

    @Override
    public List<PackingDto> findAllPackings() {
        return beanMappingService.mapToCollectionEnforceID(packingService.getAll(),
                PackingDto.class);
    }

    @Override
    public List<PackingDto> findPackingByVolume(BigDecimal volume) {
        return beanMappingService.mapToCollectionEnforceID(packingService.findPackingByVolume(volume),
                PackingDto.class);
    }

    @Override
    public List<PackingDto> findPackingByValidFrom(DateTime validFrom) {
        return beanMappingService.mapToCollectionEnforceID(packingService.findPackingByValidFrom(validFrom),
                PackingDto.class);
    }

    @Override
    public List<PackingDto> findPackingByValidTo(DateTime validTo) {
        return beanMappingService.mapToCollectionEnforceID(packingService.findPackingByValidTo(validTo),
                PackingDto.class);
    }

    @Override
    public List<PackingDto> findPackingByWine(WineDto wineDto) {
        return beanMappingService.mapToCollectionEnforceID(packingService.findPackingByWine(wineDto),
                PackingDto.class);
    }

    @Override
    public List<PackingDto> findPackingValidForDate(DateTime dateTime) {
        return beanMappingService.mapToCollectionEnforceID(packingService.findPackingValidForDate(dateTime),
                PackingDto.class);
    }

    private Packing convertDto(PackingDto dto) {
        Packing p = new Packing();
        p.setWine(wineService.get(dto.getId())); //alebo convertWineToDto ?
        p.setVolume(dto.getVolume());
        p.setValidFrom(dto.getValidFrom());
        p.setValidTo(dto.getValidTo());
        return p;
    }
}
