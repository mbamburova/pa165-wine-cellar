package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dao.PackingDao;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.Packing;
import cz.muni.fi.pa165.facade.MarketingEventFacade;
import cz.muni.fi.pa165.facade.PackingFacade;
import cz.muni.fi.pa165.facade.PriceFacade;
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
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.12.11
 */
@Component
@Controller
@RequestMapping("/prices")
public class PriceController {

    @Inject
    private PriceFacade priceFacade;

    @Inject
    private PackingFacade packingFacade;
    
    @Inject
    private MarketingEventFacade marketingEventFacade;
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("prices", priceFacade.findAllPrices());
        return "prices/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCategory(Model model) {
        model.addAttribute("priceCreate", new PriceCreateDto());
        return "prices/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("priceCreate") PriceCreateDto formBean, BindingResult bindingResult,
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
            return "prices/new";
        }
        //create product
        priceFacade.createPrice(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Price " + formBean.getPrice() + " for packing with ID " + formBean.getPackingId() + " was created");
        return "redirect:" + uriBuilder.path("/prices/index").buildAndExpand(formBean.getId()).encode().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        PriceDto priceDto = priceFacade.findPriceById(id);
        priceFacade.deletePrice(priceDto);
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
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                //      log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                //    log.trace("FieldError: {}", fe);
            }
            return "prices/update";
        }
        //create product
        priceFacade.updatePrice(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Price " + formBean.getPrice() + " for packing with ID " + formBean.getPacking().getId() + " was updated");
        return "redirect:" + uriBuilder.path("/prices/index").buildAndExpand(formBean.getId()).encode().toUriString();
    }

    @ModelAttribute("currencies")
    public List<String> currencies() {
        return Arrays.asList("CZK", "EUR", "USD");
    }

    @ModelAttribute("packings")
    public List<PackingDto> wines() {
        return packingFacade.findAllPackings();
    }
    
    @ModelAttribute("marketingevents")
    public List<MarketingEventDto> categories() {
        return marketingEventFacade.findAllMarketingEvents();
    }
}