package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PackingDao;
import cz.muni.fi.pa165.dao.PriceDao;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.entity.Price;
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

    @Autowired
    private PriceDao priceDao;

    @Override
    public void create(Packing packing) {
        packingDao.createPacking(packing);
    }

    @Override
    public Packing get(Long id) {
        return packingDao.findPackingById(id);
    }

    @Override
    public List<Packing> getAll() {
        return packingDao.findAllPackings();
    }

    @Override
    public void update(Packing packing) {
        packingDao.updatePacking(packing);
    }

    @Override
    public void updateVolume(Packing packing, BigDecimal volume) {
        packing.setVolume(volume);
        packingDao.updatePacking(packing);
    }

    @Override
    public void updateValidFrom(Packing packing, LocalDateTime validFrom) {
        packing.setValidFrom(validFrom);
        packingDao.updatePacking(packing);
    }

    @Override
    public void updateValidTo(Packing packing, LocalDateTime validTo) {
        packing.setValidTo(validTo);
        packingDao.updatePacking(packing);
    }

    @Override
    public void delete(Packing packing) {
        packingDao.deletePacking(packing);
    }

    @Override
    public boolean isValid(Packing packing) {
        return packing.getValidFrom().isBefore(LocalDateTime.now()) && packing.getValidTo().isAfter(LocalDateTime.now());
    }

    @Override
    public void addPrice(Packing packing, Price price) {

    }

    @Override
    public void removePrice(Packing packing, Price price) {

    }
}
