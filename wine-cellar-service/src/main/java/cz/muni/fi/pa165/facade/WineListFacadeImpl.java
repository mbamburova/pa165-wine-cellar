package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.WineListDto;
import org.joda.time.LocalDateTime;

import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public class WineListFacadeImpl implements WineListFacade {

    @Override
    public void create(WineListDto wineList) {

    }

    @Override
    public void delete(Long wineListId) {

    }

    @Override
    public void update(Long wineListId) {

    }

    @Override
    public void updateName(Long wineListId, String name) {

    }

    @Override
    public void updateDate(Long wineListId, LocalDateTime date) {

    }

    @Override
    public void updateMarketingEvent(Long wineListId, MarketingEventDto marketingEventDto) {

    }

    @Override
    public List<WineListDto> findAll() {
        return null;
    }

    @Override
    public WineListDto get(Long id) {
        return null;
    }

    @Override
    public List<WineListDto> findByName(String name) {
        return null;
    }

    @Override
    public List<WineListDto> findByDate(LocalDateTime date) {
        return null;
    }

    @Override
    public List<WineListDto> findByMarketingEvent(MarketingEventDto marketingEventDto) {
        return null;
    }

    @Override
    public WineListDto addWine(Long wineListId, Long wineId) {
        return null;
    }

    @Override
    public WineListDto removeWine(Long wineListId, Long wineId) {
        return null;
    }
}
