package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.WineListCreateDto;
import cz.muni.fi.pa165.dto.WineListDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public interface WineListFacade {

    Long createWineList(WineListCreateDto wineList);
    void deleteWineList(Long wineListId);
    void updateWineList(WineListDto wineList);

    List<WineListDto> findAllWineLists();
    WineListDto findWineListById(Long id);

    List<WineListDto> findWineListsByName(String name);
    List<WineListDto> findWineListsByDate(LocalDateTime date);
    List<WineListDto> findWineListsByMarketingEvent(MarketingEventDto marketingEventDto);

//    void addWine(WineListDto wineListDto, WineDto wineDto);
}
