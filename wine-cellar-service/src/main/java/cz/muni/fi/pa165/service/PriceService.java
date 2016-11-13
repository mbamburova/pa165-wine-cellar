package cz.muni.fi.pa165.service;

import java.util.List;
import cz.muni.fi.pa165.entity.Price;
import org.springframework.stereotype.Service;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

@Service
public interface PriceService {

    Price createPrice(Price p);
    Price updatePrice(Price p);
    void deletePrice(Price p);
    Price getById(Long id);
    List<Price> getAllPrices();

}
