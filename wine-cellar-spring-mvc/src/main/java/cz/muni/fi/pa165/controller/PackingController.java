package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.PackingCreateDto;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.facade.PackingFacade;
import cz.muni.fi.pa165.facade.WineFacade;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.12.10
 */
@Component
@Controller
@RequestMapping("/packings")
public class PackingController {

    @Inject
    private PackingFacade packingFacade;

    @Inject
    private WineFacade wineFacade;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("packings", packingFacade.findAllPackings());
        return "packings/index";
    }

    @ModelAttribute("wines")
    public List<WineDto> categories() {
        return wineFacade.findAllWines();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCategory(Model model) {
        model.addAttribute("packingCreate", new PackingCreateDto());
        return "packings/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("packingCreate") PackingCreateDto formBean, BindingResult bindingResult,
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
            return "packings/new";
        }
        packingFacade.createPacking(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Packing from" + formBean.getValidFrom() + " to " + formBean.getValidTo() + " for wine with ID " + formBean.getId() + " was created");
        return "redirect:" + uriBuilder.path("/packings/index").buildAndExpand(formBean.getId()).encode().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        PackingDto packingDto = packingFacade.findPackingById(id);
        packingFacade.deletePacking(packingDto);
        redirectAttributes.addFlashAttribute("alert_success", "Packing from" + packingDto.getValidFrom() + " to " + packingDto.getValidTo() + " for wine with ID " + packingDto.getId() + " was deleted.");
        return "redirect:" + uriBuilder.path("/packings/index").toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        PackingDto packingDto = packingFacade.findPackingById(id);
        model.addAttribute("packingUpdate", packingDto);
        return "packings/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("packingUpdate") PackingCreateDto formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                //      log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                //    log.trace("FieldError: {}", fe);
            }
            return "packings/update";
        }
        //create product
        packingFacade.updatePacking(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Packing from" + formBean.getValidFrom() + " to " + formBean.getValidTo() + " for wine with ID " + formBean.getId() + " was updated");
        return "redirect:" + uriBuilder.path("/packings/index").buildAndExpand(formBean.getId()).encode().toUriString();
    }

}
