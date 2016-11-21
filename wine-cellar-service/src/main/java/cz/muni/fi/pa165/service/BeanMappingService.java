package cz.muni.fi.pa165.service;

import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

public interface BeanMappingService {

    public Mapper getMapper();
    public <T> T mapTo(Object objectToMap, Class<T> classToMapTo);
    public <T> T mapToEnforceID(Object objectToMap, Class<T> classToMapTo);
    public <T> List<T> mapToCollection(Collection<?> objects, Class<T> mapToClass);
    <T> List<T> mapToCollectionEnforceID(Collection<?> objectsToMap, Class<T> classToMapTo);
    public Object mapToDTOWithID(Object objectToMap);
}
