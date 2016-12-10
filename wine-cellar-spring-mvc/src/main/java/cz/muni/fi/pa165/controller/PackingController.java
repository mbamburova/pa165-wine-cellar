package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.PackingCreateDto;
import cz.muni.fi.pa165.dto.PackingDto;
import cz.muni.fi.pa165.facade.PackingFacade;
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

//    @Inject
//    private PackingFacade packingFacade;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {

        List<PackingCreateDto> packings = new ArrayList<>();

        PackingCreateDto packingCreateDto = new PackingCreateDto();
        packingCreateDto.setId(1L);
        packingCreateDto.setWineId(1L);
        packingCreateDto.setValidFrom(new LocalDateTime(2016,12,1,0,0));
        packingCreateDto.setValidTo(new LocalDateTime(2016,12,31,0,0));

        packings.add(packingCreateDto);

        model.addAttribute("packings", packings);


        return "packings/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCategory(Model model) {
        model.addAttribute("packingCreate", new PackingCreateDto());
        return "packings/new";
    }

//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
//    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
//        PackingDto packingDto = packingFacade.findPackingById(id);
//        packingFacade.deletePacking(packingDto);
//        redirectAttributes.addFlashAttribute("alert_success", "Packing was deleted.");
//        return "redirect:" + uriBuilder.path("/packing/list").toUriString();
//    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String create(@Valid @ModelAttribute("packingCreate") PackingCreateDto formBean, BindingResult bindingResult,
//                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
//        //log.debug("create(productCreate={})", formBean);
//        //in case of validation error forward back to the the form
//        if (bindingResult.hasErrors()) {
//            for (ObjectError ge : bindingResult.getGlobalErrors()) {
//                //      log.trace("ObjectError: {}", ge);
//            }
//            for (FieldError fe : bindingResult.getFieldErrors()) {
//                model.addAttribute(fe.getField() + "_error", true);
//                //    log.trace("FieldError: {}", fe);
//            }
//            return "product/new";
//        }
//        //create product
//        packingFacade.createPacking(formBean);
//        //report success
//        redirectAttributes.addFlashAttribute("alert_success", "Packing was created");
//        return "redirect:" + uriBuilder.path("/packings/index");
//    }

}
