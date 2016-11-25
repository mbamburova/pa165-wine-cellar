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
    public void createWineList(WineListDto wineList) {

    }

    @Override
    public void deleteWineList(Long wineListId) {

    }

    @Override
    public void updateWineList(Long wineListId) {

    }

    @Override
    public List<WineListDto> findAllWineLists() {
        return null;
    }

    @Override
    public WineListDto findWineListById(Long id) {
        return null;
    }

    @Override
    public List<WineListDto> findWineListsByName(String name) {
        return null;
    }

    @Override
    public List<WineListDto> findWineListsByDate(LocalDateTime date) {
        return null;
    }

    @Override
    public List<WineListDto> findWineListsByMarketingEvent(MarketingEventDto marketingEventDto) {
        return null;
    }
}
