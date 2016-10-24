package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Wine;

import java.util.List;

/**
 * @author Michaela Bamburová on 25.10.2016.
 */
public interface WineDao {

    void createWine(Wine wine);
    void updateWine(Wine wine);
    void deleteWine(Wine wine);
    Wine getWineById(Long id);
    List<Wine> getAllWines();
}
