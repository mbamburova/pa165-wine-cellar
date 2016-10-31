package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Packing;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Michaela Bamburová on 25.10.2016.
 */
public interface PackingDao {

    /**
     * Creates new packing.
     * @param p
     */
    void createPacking(Packing p);

    /**
     * Deletes given packing.
     * @param p
     */
    void deletePacking(Packing p);

    /**
     * Updates given packing.
     * @param p
     */
    void updatePacking(Packing p);

    /**
     * Finds packing by given id.
     * @param id
     * @return
     */
    Packing findById(Long id);

    /**
     * Finds packings by given volume.
     * @param volume
     * @return
     */
    List<Packing> findPackingsByVolume(BigDecimal volume);

    /**
     * Finds all packings.
     * @return
     */
    List<Packing> findAll();
}
