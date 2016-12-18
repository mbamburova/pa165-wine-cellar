package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.facade.MarketingEventFacade;
import cz.muni.fi.pa165.facade.PackingFacade;
import cz.muni.fi.pa165.facade.PriceFacade;
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
import java.util.Arrays;
import java.util.List;

/**
 * @author Tomas Gordian
 * @version 2016.12.11
 */

@Controller
@RequestMapping("/prices")
public class PriceController {

    @Inject
    private PriceFacade priceFacade;

    @Inject
    private PackingFacade packingFacade;
    
    @Inject
    private MarketingEventFacade marketingEventFacade;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPrice(Model model) {
        model.addAttribute("pricePacking", new PricePackingCreateDto());
        model.addAttribute("currencies");
        model.addAttribute("marketingevents");
        return "prices/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("pricePacking") PricePackingCreateDto formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "prices/new";
        }
//        Long id = priceFacade.createPrice(formBean);
//        redirectAttributes.addFlashAttribute("alert_success", "Price " + formBean.getPrice() + " for packing with ID " + formBean.getPackingId() + " was created");
        return "redirect:" + uriBuilder.path("/wines/index").buildAndExpand().encode().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        PriceDto priceDto = priceFacade.findPriceById(id);
        priceFacade.deletePrice(id);
        redirectAttributes.addFlashAttribute("alert_success", "Price " + priceDto.getPrice() + " for packing with ID " + priceDto.getPacking().getId() + " was deleted.");
        return "redirect:" + uriBuilder.path("/prices/index").toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        PriceDto priceDto = priceFacade.findPriceById(id);

        model.addAttribute("priceUpdate", priceDto);
        return "prices/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("priceUpdate") PriceDto formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
             for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "prices/update";
        }

        priceFacade.updatePrice(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Price " + formBean.getPrice() + " for packing with ID " +
            formBean.getPacking().getVolume()+ " was updated");
        return "redirect:" + uriBuilder.path("/prices/index").buildAndExpand(formBean.getId()).encode().toUriString();
    }

    @ModelAttribute("currencies")
    public List<String> currencies() {
        return Arrays.asList("CZK", "EUR", "USD");
    }


    
    @ModelAttribute("marketingevents")
    public List<MarketingEventDto> categories() {
        return marketingEventFacade.findAllMarketingEvents();
    }
}