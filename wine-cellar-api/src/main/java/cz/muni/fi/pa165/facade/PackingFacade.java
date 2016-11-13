package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.PackingDto;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

public interface PackingFacade {

   // Long createPacking(PackingCreateDto p);
    void deletePacking(Long id);

}
