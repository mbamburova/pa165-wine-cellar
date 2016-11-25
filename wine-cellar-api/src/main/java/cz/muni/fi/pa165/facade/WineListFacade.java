package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.WineListDto;
import org.joda.time.LocalDateTime;

import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public interface WineListFacade {

    void createWineList(WineListDto wineList);
    void deleteWineList(WineListDto wineList);
    void updateWineList(WineListDto wineList);

    List<WineListDto> findAllWineLists();
    WineListDto findWineListById(Long id);

    List<WineListDto> findWineListsByName(String name);
    List<WineListDto> findWineListsByDate(LocalDateTime date);
    List<WineListDto> findWineListsByMarketingEvent(MarketingEventDto marketingEventDto);
}
