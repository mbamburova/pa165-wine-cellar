package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Packing;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Michaela Bamburová on 25.10.2016.
 */
public interface PackingDao {

    void createPacking(Packing p);
    void deletePacking(Packing p);
    void updatePacking(Packing p);
    Packing findById(Long id);
    List<Packing> findPackingsByVolume(BigDecimal volume);
    List<Packing> findAll();
}
