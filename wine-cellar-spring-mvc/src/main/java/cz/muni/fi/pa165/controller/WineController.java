package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.facade.WineFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
@Controller
@RequestMapping("/wines")
public class WineController {

    @Inject
    private WineFacade wineFacade;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(value = "name", required = false) String title,
                                     @RequestParam(value = "vintage", required = false)String vintage) {

        List<WineDto> wines = wineFacade.findAllWines();

        model.addAttribute("wines", wines);
        return "wines/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProduct(Model model) {
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
    public String create(@Valid @ModelAttribute("wineCreate") WineDto formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
           for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "wines/new";
        }


        Long id = wineFacade.createWine(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Wine " + id + " was created");
        return "redirect:" + uriBuilder.path("/wines/index").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        WineDto wineDto = wineFacade.findWineById(id);
        model.addAttribute("wineUpdate", wineDto);
        return "wines/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("wineUpdate") WineDto formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {

            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);

            }
            return "wines/update";
        }

        wineFacade.updateWine(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Wine " + formBean.getDescription() + " was updated");
        return "redirect:" + uriBuilder.path("/wines/index").buildAndExpand(formBean.getId()).encode().toUriString();
    }
}
