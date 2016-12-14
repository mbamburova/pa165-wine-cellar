package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.*;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */

@Service
public class BeanMappingServiceImpl implements BeanMappingService {

    private Mapper mapper = new DozerBeanMapper(Arrays.asList(new String[]{"dozer.xml"}));

    @Override
    public Mapper getMapper() {
        return mapper;
    }

    @Override
    public <T> T mapTo(Object objectToMap, Class<T> classToMapTo) {
        return mapper.map(objectToMap, classToMapTo);
    }

    @Override
    public <T> T mapToEnforceID(Object objectToMap, Class<T> classToMapTo) {
        T mappedObjectWithId = (T) mapToDTOWithID(objectToMap);
        if(mappedObjectWithId != null) {
            return mappedObjectWithId;
        }
        return mapper.map(objectToMap, classToMapTo);
    }

    @Override
    public <T> List<T> mapToCollection(Collection<?> objectsToMap, Class<T> classToMapTo) {
        List<T> mappedObjects = new ArrayList<>();
        for (Object object : objectsToMap)
        {
            mappedObjects.add(mapper.map(object, classToMapTo));
        }
        return mappedObjects;
    }

    @Override
    public <T> List<T> mapToCollectionEnforceID(Collection<?> objectsToMap, Class<T> classToMapTo) {

        List<T> mappedObjects = new ArrayList<>();
        for (Object object : objectsToMap)
        {
            T mappedObjectWithId = (T) mapToDTOWithID(object);
            if(mappedObjectWithId != null) {
                mappedObjects.add(mappedObjectWithId);
            }
            else {
                mappedObjects.add(mapper.map(object, classToMapTo));
            }

        }
        return mappedObjects;
    }

    public Object mapToDTOWithID(Object objectToMap){

        if (objectToMap instanceof MarketingEvent){

            MarketingEvent marketingEventToMap = (MarketingEvent)objectToMap;
            Object mappedObject = mapper.map(marketingEventToMap, MarketingEventDto.class);
            MarketingEventDto mappedMarketingEvent = (MarketingEventDto) mappedObject;
            mappedMarketingEvent.setId(marketingEventToMap.getId());
            return mappedMarketingEvent;
        }
        if (objectToMap instanceof Packing){

            Packing packingToMap = (Packing) objectToMap;
            Object mappedObject = mapper.map(packingToMap, PackingDto.class);
            PackingDto mappedPacking = (PackingDto) mappedObject;
            mappedPacking.setId(packingToMap.getId());
            return mappedPacking;
        }
        if (objectToMap instanceof Price){

            Price priceToMap = (Price) objectToMap;
            Object mappedObject = mapper.map(priceToMap, PriceDto.class);
            PriceDto mappedPrice = (PriceDto) mappedObject;
            mappedPrice.setId(priceToMap.getId());
            return mappedPrice;
        }
        if (objectToMap instanceof Wine){

            Wine wineToMap = (Wine)objectToMap;
            Object mappedObject = mapper.map(wineToMap, WineDto.class);
            WineDto mappedWine = (WineDto) mappedObject;
            mappedWine.setId(wineToMap.getId());
            return mappedWine;
        }
        if (objectToMap instanceof WineList){

            WineList wineListToMap = (WineList) objectToMap;
            Object mappedObject = mapper.map(wineListToMap, WineListDto.class);
            WineListDto mappedWineList = (WineListDto) mappedObject;
            mappedWineList.setId(wineListToMap.getId());
            return mappedWineList;
        }

        return null;
    }
}
