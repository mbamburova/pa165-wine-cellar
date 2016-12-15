package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.WineListDto;
import cz.muni.fi.pa165.facade.WineListFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.12.14
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_WINELISTS)
public class WineListController {

    @Inject
    private WineListFacade wineListFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<WineListDto> getWineList(@RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            return wineListFacade.findWineListsByName(name);
        } else {
            return wineListFacade.findAllWineLists();
        }
    }


}
