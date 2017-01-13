package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.wineList.WineListCreateDto;
import cz.muni.fi.pa165.dto.wineList.WineListDto;
import cz.muni.fi.pa165.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.exceptions.ResourceNotModifiedException;
import cz.muni.fi.pa165.facade.WineListFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.12.15
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_WINELISTS)
public class WineListController {

    @Inject
    private WineListFacade wineListFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<WineListDto> getAlbums(@RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            return wineListFacade.findWineListsByName(name);
        } else {
            return wineListFacade.findAllWineLists();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final WineListDto getWineList(@PathVariable("id") long id) throws Exception {
        try {
            return wineListFacade.findWineListById(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final WineListDto createWineList(@RequestBody WineListCreateDto wineList) throws Exception {
        try {
            Long id = wineListFacade.createWineList(wineList);
            return wineListFacade.findWineListById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final WineListDto updateWineList(@PathVariable("id") long id, @RequestBody WineListDto editedWineList) throws Exception {

        try {
            editedWineList.setId(id);
            wineListFacade.updateWineList(editedWineList);
        } catch (Exception ex) {
            throw new ResourceNotModifiedException();
        }

        return editedWineList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void removeWineList(@PathVariable("id") long id) throws Exception {
        try {
            wineListFacade.deleteWineList(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

}
