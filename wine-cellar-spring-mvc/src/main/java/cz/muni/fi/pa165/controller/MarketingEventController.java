package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.facade.MarketingEventFacade;
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

/**
 * @author MarekScholtz
 * @version 2016.12.11
 */
@Component
@Controller
@RequestMapping("/marketingevents")
public class MarketingEventController {

    @Inject
    MarketingEventFacade marketingEventFacade;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("marketingevents", marketingEventFacade.findAllMarketingEvents());
        return "marketingevents/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCategory(Model model) {
        model.addAttribute("marketingEventCreate", new MarketingEventDto());
        return "marketingevents/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("marketingEventCreate") MarketingEventDto formBean, BindingResult bindingResult,
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
            return "marketingevents/new";
        }
        //create product
        marketingEventFacade.createMarketingEvent(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Marketing Event " + formBean.getDescription() + " was created");
        return "redirect:" + uriBuilder.path("/marketingevents/index").buildAndExpand(formBean.getId()).encode().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        MarketingEventDto marketingEventDto = marketingEventFacade.findMarketingEventById(id);
        marketingEventFacade.deleteMarketingEvent(marketingEventDto);
        redirectAttributes.addFlashAttribute("alert_success", "Marketing Event \"" + marketingEventDto.getDescription() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/marketingevents/index").toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        MarketingEventDto marketingEventDto = marketingEventFacade.findMarketingEventById(id);
        model.addAttribute("marketingEventUpdate", marketingEventDto);
        return "marketingevents/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("marketingEventCreate") MarketingEventDto formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                //      log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                //    log.trace("FieldError: {}", fe);
            }
            return "marketingevents/update";
        }
        //create product
        marketingEventFacade.updateMarketingEvent(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Marketing Event " + formBean.getDescription() + " was updated");
        return "redirect:" + uriBuilder.path("/marketingevents/index").buildAndExpand(formBean.getId()).encode().toUriString();
    }

}
