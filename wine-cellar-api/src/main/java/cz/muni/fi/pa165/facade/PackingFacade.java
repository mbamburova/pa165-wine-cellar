package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.PackingDTO;

import java.util.List;

/**
 * @author Michaela Bamburová on 25.10.2016.
 */
public interface PackingFacade {

    List<PackingDTO> getAllPackings();
    PackingDTO getPackingById(Long id);
}
