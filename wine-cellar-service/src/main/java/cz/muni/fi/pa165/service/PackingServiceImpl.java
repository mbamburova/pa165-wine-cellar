package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PackingDao;
import cz.muni.fi.pa165.dao.PriceDao;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.entity.Wine;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.11.06
 */
@Service
public class PackingServiceImpl implements PackingService {

    @Autowired
    private PackingDao packingDao;

    @Override
    public void createPacking(Packing packing) {
        packingDao.createPacking(packing);
    }

    @Override
    public Packing findPackingById(Long id) {
        return packingDao.findPackingById(id);
    }

    @Override
    public List<Packing> findAllPackings() {
        return packingDao.findAllPackings();
    }

    @Override
    public List<Packing> findPackingsByVolume(BigDecimal volume) {
        return packingDao.findPackingsByVolume(volume);
    }

    @Override
    public List<Packing> findPackingsByWine(Wine wine) {
        return packingDao.findPackingsByWine(wine);
    }

    @Override
    public void updatePacking(Packing packing) {
        packingDao.updatePacking(packing);
    }

    @Override
    public void deletePacking(Packing packing) {
        packingDao.deletePacking(packing);
    }

    @Override
    public boolean isPackingValid(Packing packing) {
        return packing.getValidFrom().isBefore(LocalDateTime.now()) && packing.getValidTo().isAfter(LocalDateTime.now());
    }
}
