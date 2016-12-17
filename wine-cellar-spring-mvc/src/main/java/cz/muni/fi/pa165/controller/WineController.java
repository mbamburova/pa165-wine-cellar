package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.WineCreateDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.dto.WineListDto;
import cz.muni.fi.pa165.dto.WineUpdateDto;
import cz.muni.fi.pa165.facade.WineFacade;
import cz.muni.fi.pa165.facade.WineListFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michaela Bamburova on 16/12/2016.
 */
@Controller
@RequestMapping("/wines")
public class WineController {

    @Inject
    private WineFacade wineFacade;

    @Inject
    private WineListFacade wineListFacade;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(value = "name", required = false) String title,
                                     @RequestParam(value = "vintage", required = false)String vintage) {

        List<WineDto> wines = wineFacade.findAllWines();
        List<WineListDto> wineLists = wineListFacade.findAllWineLists();

        model.addAttribute("wines", wines);
        model.addAttribute("wineLists", wineLists);
        return "wines/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newWine(Model model) {

        model.addAttribute("vintageValues", vintageValues());
        model.addAttribute("wineCreate", new WineDto());
        return "wines/new";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        WineDto wineDto = wineFacade.findWineById(id);
        wineFacade.deleteWine(id);
        redirectAttributes.addFlashAttribute("alert_success", "Wine \"" + wineDto.getName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/wines/index").toUriString();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("wineCreate") WineCreateDto formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
           for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            model.addAttribute("vintageValues", vintageValues());
            return "wines/new";
        }
        Long id = wineFacade.createWine(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Wine " + formBean.getName() + " was created");
        return "redirect:" + uriBuilder.path("/wines/index").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        WineDto wineDto = wineFacade.findWineById(id);
        WineUpdateDto toUpdate = wineFacade.toWineUpdateDto(wineDto);

        model.addAttribute("vintageValues", vintageValues());
        model.addAttribute("wineUpdate", toUpdate);
        return "wines/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("wineUpdate") WineUpdateDto formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            model.addAttribute("vintageValues", vintageValues());
            return "redirect:" + uriBuilder.path("/wines/update").buildAndExpand(formBean.getId()).encode().toUriString();
        }

        wineFacade.updateWine(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Wine " + formBean.getDescription() + " was updated");
        return "wines/index";
    }

    @RequestMapping(value="add/{id}", method = RequestMethod.GET)
    public String add(@PathVariable long id, WineListDto wineListDto, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        WineDto wine = wineFacade.findWineById(id);
        wineListDto.addWine(wine);

        redirectAttributes.addFlashAttribute("alert_success", "Wine " + wine.getName() + " was added to tasting ticket " + wineListDto.getName());
        return "redirect:" + uriBuilder.path("/wines/index").buildAndExpand(id).encode().toUriString();
    }

    @ModelAttribute("vintageValues")
    private List<Integer> vintageValues() {
        List<Integer> values = new ArrayList<>();
        int maxVintage = 2020;

        for (int i = 1990; i < maxVintage; i++) {
            values.add(i);
        }
        return values;
    }

    public List<WineDto> removeWines(List<WineDto> wines, String atribute, String value) {

        List<WineDto> removedWines = new ArrayList<>();
        removedWines.addAll(wines);

        if(atribute.equals("vintage")) {

            for (WineDto wine : wines) {
                if (wine.getVintage().toString() != value) {
                    removedWines.remove(wine);
                }
            }
        }
        return removedWines;
    }
}
