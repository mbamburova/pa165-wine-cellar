package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.WineListDto;
import org.joda.time.LocalDateTime;

import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public interface WineListFacade {

    void create(WineListDto wineList);
    void delete(Long wineListId);
    void update(Long wineListId);
    void updateName(Long wineListId, String name);
    void updateDate(Long wineListId, LocalDateTime date);
    void updateMarketingEvent(Long wineListId, MarketingEventDto marketingEventDto);

    List<WineListDto> findAll();
    WineListDto get(Long id);
    List<WineListDto> findByName(String name);
    List<WineListDto> findByDate(LocalDateTime date);
    List<WineListDto> findByMarketingEvent(MarketingEventDto marketingEventDto);

    WineListDto addWine(Long wineListId, Long wineId);
    WineListDto removeWine(Long wineListId, Long wineId);
}
