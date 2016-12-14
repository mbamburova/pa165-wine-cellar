package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.PackingCreateDto;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.facade.PackingFacade;
import cz.muni.fi.pa165.facade.WineFacade;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.12.10
 */

@Controller
@RequestMapping("pa165/packings")
public class PackingController {

    @Inject
    private PackingFacade packingFacade;

    @Inject
    private WineFacade wineFacade;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        List<PackingCreateDto> packings = new ArrayList<>();
        for (PackingDto packingDto : packingFacade.findAllPackings()) {
            PackingCreateDto packingCreateDto = new PackingCreateDto();
            packingCreateDto.setVolume(packingDto.getVolume());
            packingCreateDto.setValidFrom(packingDto.getValidFrom());
            packingCreateDto.setValidTo(packingDto.getValidTo());
            packingCreateDto.setWineId(packingDto.getWine().getId());
            packings.add(packingCreateDto);
        }
        model.addAttribute("packings", packings);
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

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {

            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);

            }
            return "packings/new";
        }
        PackingDto packingDto = new PackingDto();
        packingDto.setVolume(formBean.getVolume());

        packingDto.setValidFrom(formBean.getValidFrom()));
        packingDto.setValidTo(formBean.getValidTo()));
        packingDto.setWine(wineFacade.findWineById(formBean.getWineId()));
        packingFacade.createPacking(packingDto);
        redirectAttributes.addFlashAttribute("alert_success", "Packing from " + formBean.getValidFrom() + " to " + formBean.getValidTo() + " for wine " + packingDto.getWine().getName() + " was created");
        return "redirect:" + uriBuilder.path("/packings/index").buildAndExpand(formBean.getId()).encode().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        PackingDto packingDto = packingFacade.findPackingById(id);
        packingFacade.deletePacking(packingDto);

        redirectAttributes.addFlashAttribute("alert_success", "Packing from " + packingDto.getValidFrom() + " to " + packingDto.getValidTo() + " for wine " + packingDto.getWine().getName() + " was deleted.");
        return "redirect:" + uriBuilder.path("/packings/index").toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        PackingDto packingDto = packingFacade.findPackingById(id);
        PackingCreateDto packingCreateDto = new PackingCreateDto();
        packingCreateDto.setVolume(packingDto.getVolume());
        packingCreateDto.setValidFrom(packingDto.getValidFrom());
        packingCreateDto.setValidTo(packingDto.getValidTo());
        packingCreateDto.setWineId(packingDto.getWine().getId());
        model.addAttribute("packingUpdate", packingCreateDto);
        return "packings/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("packingUpdate") PackingDto formBean, BindingResult bindingResult,
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
        PackingDto packingDto = new PackingDto();
        packingDto.setId(formBean.getId());
        packingDto.setVolume(formBean.getVolume());
        packingDto.setValidFrom(LocalDateTime.parse(formBean.getValidFrom());
        packingDto.setValidTo(LocalDateTime.parse(formBean.getValidTo());
        packingDto.setWine(wineFacade.findWineById(formBean.getWineId()));
        packingFacade.updatePacking(packingDto);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Packing from " + packingDto.getValidFrom() + " to " + packingDto.getValidTo() + " for wine " + packingDto.getWine().getName() + " was updated");
        return "redirect:" + uriBuilder.path("/packings/index").buildAndExpand(formBean.getId()).encode().toUriString();
    }

}
