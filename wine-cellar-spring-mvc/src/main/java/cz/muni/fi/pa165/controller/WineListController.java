package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.dto.WineListCreateDto;
import cz.muni.fi.pa165.dto.WineListDto;
import cz.muni.fi.pa165.facade.MarketingEventFacade;
import cz.muni.fi.pa165.facade.WineFacade;
import cz.muni.fi.pa165.facade.WineListFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
 * @author Tomas Gordian on 11/6/2016.
 */
@Controller
@RequestMapping("pa165/winelists")
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

    @ModelAttribute("wines")
    public List<WineDto> wineListWines() {
        return wineFacade.findAllWines();
    }

    @ModelAttribute("marketingEvents")
    public List<MarketingEventDto> marketingEvents() {
        return marketingEventFacade.findAllMarketingEvents();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        WineListDto wineListDto = wineListFacade.findWineListById(id);
        wineListFacade.deleteWineList(wineListDto);
        redirectAttributes.addFlashAttribute("alert_success", "WineList \"" + wineListDto.getName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/winelists/index").toUriString();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("wineListCreate") WineListCreateDto formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {

            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);

            }
            return "winelists/new";
        }

        WineListDto wineListDto = new WineListDto();

        wineListDto.setDate(formBean.getDate());

        for (Long wineId : formBean.getWinesIds()) {
            wineListDto.addWine(wineFacade.findWineById(wineId));
        }
        if(formBean.getMarketingEventId() != null) {
            wineListDto.setMarketingEvent(marketingEventFacade.findMarketingEventById(formBean.getMarketingEventId()));
        }
        wineListDto.setName(formBean.getName());
        wineListFacade.createWineList(wineListDto);

        redirectAttributes.addFlashAttribute("alert_success", "WineList " + formBean.getName() + " was created");
        return "redirect:" + uriBuilder.path("/winelists/index").buildAndExpand(formBean.getId()).encode().toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewWines(@PathVariable long id, Model model) {
        model.addAttribute("wineListView", wineListFacade.findWineListById(id));
        return "winelists/view";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        WineListDto wineListDto = wineListFacade.findWineListById(id);

        WineListCreateDto wineListCreateDto = new WineListCreateDto();
        wineListCreateDto.setName(wineListDto.getName());
        //wineListCreateDto.setDate(wineListDto.getDate());

        wineListCreateDto.setDate((wineListDto.getDate()));

        if(wineListDto.getMarketingEvent() != null) {
            wineListCreateDto.setMarketingEventId(wineListDto.getMarketingEvent().getId());
        }
        for (WineDto wineDto : wineListDto.getWines()) {
            wineListCreateDto.addWine(wineDto.getId());
        }

        model.addAttribute("wineListUpdate", wineListCreateDto);
        return "winelists/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("wineListUpdate") WineListCreateDto formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {

            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "winelists/update";
        }

        WineListDto wineListDto = new WineListDto();
        wineListDto.setId(formBean.getId());

        wineListDto.setDate(formBean.getDate());

        for (Long wineId : formBean.getWinesIds()) {
            wineListDto.addWine(wineFacade.findWineById(wineId));
        }
        wineListDto.setMarketingEvent(marketingEventFacade.findMarketingEventById(formBean.getMarketingEventId()));
        wineListDto.setName(formBean.getName());

        wineListFacade.updateWineList(wineListDto);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "WineList " + formBean.getName() + " was updated");
        return "redirect:" + uriBuilder.path("/winelists/index").buildAndExpand(formBean.getId()).encode().toUriString();
    }
}
