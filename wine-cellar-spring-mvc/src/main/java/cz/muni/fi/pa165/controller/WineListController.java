package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.marketingEvent.MarketingEventDto;
import cz.muni.fi.pa165.dto.wine.WineDto;
import cz.muni.fi.pa165.dto.wineList.WineListCreateDto;
import cz.muni.fi.pa165.dto.wineList.WineListDto;
import cz.muni.fi.pa165.facade.MarketingEventFacade;
import cz.muni.fi.pa165.facade.WineFacade;
import cz.muni.fi.pa165.facade.WineListFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Silvia Borzova
 *         16/12/2016.
 */
@Controller
@RequestMapping("/winelists")
public class WineListController {

    @Inject
    private WineListFacade wineListFacade;

    @Inject
    private WineFacade wineFacade;

    @Inject
    private MarketingEventFacade marketingEventFacade;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("winelists", wineListFacade.findAllWineLists());
        return "winelists/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newWineList(Model model) {
        model.addAttribute("wineListCreate", new WineListCreateDto());
        return "winelists/new";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        WineListDto wineListDto = wineListFacade.findWineListById(id);

        try {
            wineListFacade.deleteWineList(id);
            redirectAttributes.addFlashAttribute("alert_info", "Tasting ticket \"" + wineListDto.getName() + "\" was deleted.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Tasting ticket \"" + wineListDto.getName() + "\" wasn't deleted! Some error occurred!");
        }
        return "redirect:" + uriBuilder.path("/winelists/index").toUriString();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("wineListCreate") WineListCreateDto formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "winelists/new";
        }
        Long id = wineListFacade.createWineList(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Tasting ticket \"" + formBean.getName() + "\" was created.");
        return "redirect:" + uriBuilder.path("/winelists/index").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewWines(@PathVariable long id, Model model) {
        WineListDto wineListDto = wineListFacade.findWineListById(id);
        List<WineDto> wines = wineListDto.getWines();

        model.addAttribute("wineListView", wines);
        model.addAttribute("wineListDto", wineListDto);
        return "winelists/view";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        WineListDto wineListDto = wineListFacade.findWineListById(id);
        model.addAttribute("wineListUpdate", wineListDto);
        return "winelists/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("wineListUpdate") WineListDto formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "winelists/update";
        }
        WineListDto wineListDto = wineListFacade.findWineListById(formBean.getId());
        formBean.setWines(wineListDto.getWines());
        wineListFacade.updateWineList(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Tasting ticket \"" + formBean.getName() + "\" was updated.");
        return "redirect:" + uriBuilder.path("/winelists/index").buildAndExpand(formBean.getId()).encode().toUriString();
    }

    @ModelAttribute("wines")
    public List<WineDto> wineListWines() {
        return wineFacade.findAllWines();
    }

    @ModelAttribute("marketingEvents")
    public List<MarketingEventDto> marketingEvents() {
        return marketingEventFacade.findAllMarketingEvents();
    }
}
