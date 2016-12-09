package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.facade.WineFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Gordian on 12/8/2016.
 */
@Controller
@RequestMapping(value = {"/wines"})
public class WineRestController {

    @Inject
    private WineFacade wineFacade;

    /**
     * List sports in database.
     * <p>
     * If name is not specified, all sports are returned.
     * If name is specified, only sport with given name is returned
     *
     * @param model
     * @param wineName if specified, only sport with given name is returned
     * @return jsp template sports/index
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String findAll(Model model,
                        @RequestParam(value = "wineName", required = false) String wineName,
                        RedirectAttributes redirectAttributes,
                        UriComponentsBuilder uriComponentsBuilder) {

        List<WineDto> wines = null;

        if (wineName == null || wineName.isEmpty()) {
            wines = wineFacade.findAllWines();
        } else {
            wines = new ArrayList<>();
            try {
                wines = wineFacade.findWinesByName(wineName);
            } catch (IllegalArgumentException e) {

                redirectAttributes.addFlashAttribute("alert_warning", "Wine " + wineName +
                        " not found, showing all wines.");
                return "redirect:" + uriComponentsBuilder.path("/wines").build().encode().toUriString();
            }
        }

        model.addAttribute("sports", wines);

        return "sports/index";
    }
}
