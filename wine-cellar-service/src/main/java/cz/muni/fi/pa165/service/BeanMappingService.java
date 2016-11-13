package cz.muni.fi.pa165.service;

import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

public interface BeanMappingService {

    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}
