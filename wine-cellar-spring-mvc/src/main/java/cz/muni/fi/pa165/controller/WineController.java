package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.facade.WineFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    public String index(Model model) {

        model.addAttribute("wines", wineFacade.findAllWines());

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
        wineFacade.deleteWine(wineDto);
        redirectAttributes.addFlashAttribute("alert_success", "Wine \"" + wineDto.getName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/wines/index").toUriString();
    }



    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("wineCreate") WineDto formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        //log.debug("create(productCreate={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                //      log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                //    log.trace("FieldError: {}", fe);
            }
            return "wines/new";
        }
        //create product
        Long id = wineFacade.createWine(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Wine " + id + " was created");
        return "redirect:" + uriBuilder.path("/wines/index").buildAndExpand(id).encode().toUriString();
    }

}
