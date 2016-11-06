package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.WineListDto;

import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
public interface WineListFacade {
    List<WineListDto> getAllWineLists();
    WineListDto getWineListsById();
    List<WineListDto> getWineListsByName();
    List<WineListDto> getWineListsByDate();
}
