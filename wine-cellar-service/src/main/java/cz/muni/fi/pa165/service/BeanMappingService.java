package cz.muni.fi.pa165.service;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

public interface BeanMappingService {

    Mapper getMapper();
    <T> T mapTo(Object objectToMap, Class<T> classToMapTo);
    <T> T mapToEnforceID(Object objectToMap, Class<T> classToMapTo);
    <T> List<T> mapToCollection(Collection<?> objects, Class<T> mapToClass);
    <T> List<T> mapToCollectionEnforceID(Collection<?> objectsToMap, Class<T> classToMapTo);
    Object mapToDTOWithID(Object objectToMap);
}
