package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PackingDao;
import cz.muni.fi.pa165.dao.PriceDao;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
import cz.muni.fi.pa165.entity.Wine;
import cz.muni.fi.pa165.exception.WineCellarDataAccessException;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        try {
            packingDao.createPacking(packing);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("cannot create packing", e);
        }
    }

    @Override
    public Packing findPackingById(Long id) {
        try {
            return packingDao.findPackingById(id);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("cannot find packing by id", e);
        }
    }

    @Override
    public List<Packing> findAllPackings() {
        try {
            return packingDao.findAllPackings();
        } catch (Exception e) {
            throw new WineCellarDataAccessException("cannot find all packings", e);
        }
    }

    @Override
    public List<Packing> findPackingsByVolume(BigDecimal volume) {
        try {
            return packingDao.findPackingsByVolume(volume);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("cannot find packing by volume", e);
        }
    }

    @Override
    public List<Packing> findPackingsByWine(Wine wine) {
        try {
            return packingDao.findPackingsByWine(wine);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("cannot find packing by wine", e);
        }
    }

    @Override
    public void updatePacking(Packing packing) {
        try {
            packingDao.updatePacking(packing);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("cannot update packing", e);
        }
    }

    @Override
    public void deletePacking(Packing packing) {
        try {
            packingDao.deletePacking(packing);
        } catch (Exception e) {
            throw new WineCellarDataAccessException("cannot delete packing", e);
        }
    }

    @Override
    public boolean isPackingValid(Packing packing) {
        try {
            return packing.getValidFrom().isBefore(LocalDateTime.now()) && packing.getValidTo().isAfter(LocalDateTime.now());
        } catch (Exception e) {
            throw new WineCellarDataAccessException("cannot check if packing is valid", e);
        }
    }
}
