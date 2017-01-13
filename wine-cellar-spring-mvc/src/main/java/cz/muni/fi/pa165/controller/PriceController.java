package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.marketingEvent.MarketingEventDto;
import cz.muni.fi.pa165.dto.packing.PackingCreateDto;
import cz.muni.fi.pa165.dto.packing.PackingDto;
import cz.muni.fi.pa165.dto.price.PriceDto;
import cz.muni.fi.pa165.dto.price.PricePackingCreateDto;
import cz.muni.fi.pa165.dto.price.PricePackingDto;
import cz.muni.fi.pa165.dto.wine.WineDto;
import cz.muni.fi.pa165.facade.MarketingEventFacade;
import cz.muni.fi.pa165.facade.PackingFacade;
import cz.muni.fi.pa165.facade.PriceFacade;
import cz.muni.fi.pa165.facade.WineFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
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
    private WineFacade wineFacade;

    @Inject
    private MarketingEventFacade marketingEventFacade;

    @RequestMapping(value = "/new/{id}", method = RequestMethod.GET)
    public String newPrice(@PathVariable long id, Model model) {
        PackingCreateDto packingDto = new PackingCreateDto();
        packingDto.setWineId(id);
        PricePackingCreateDto pricePackingCreateDto = new PricePackingCreateDto();
        pricePackingCreateDto.setPackingDto(packingDto);

        model.addAttribute("pricePacking", pricePackingCreateDto);
        return "prices/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("pricePacking") PricePackingCreateDto formBean, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "prices/new";
        }
        WineDto wine = wineFacade.findWineById(formBean.getPackingDto().getWineId());
        Long id = packingFacade.createPacking(formBean.getPackingDto());
        formBean.getPriceDto().setPackingId(id);
        priceFacade.createPrice(formBean.getPriceDto());

        redirectAttributes.addFlashAttribute("alert_success", "Price " + formBean.getPriceDto().getPrice() + " " + formBean.getPriceDto().getCurrency() + " / " + formBean.getPackingDto().getVolume() + " l" + " for wine " + wine.getName() + " was created");

        return "redirect:" + uriBuilder.path("/wines/detail/" + formBean.getPackingDto().getWineId()).toUriString();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("priceId") Long priceId, @RequestParam("packingId") Long packingId, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        PackingDto packingDto = packingFacade.findPackingById(packingId);
        PriceDto priceDto = priceFacade.findPriceById(priceId);
        priceFacade.deletePrice(priceId);
        packingFacade.deletePacking(packingId);

        redirectAttributes.addFlashAttribute("alert_success", "Price " + priceDto.getPrice() + " " + priceDto.getCurrency() + " / " + packingDto.getVolume() + " l" + " was deleted.");

        return "redirect:" + uriBuilder.path("/wines/update/" + packingDto.getWine().getId()).toUriString();
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam("priceId") Long priceId, @RequestParam("packingId") Long packingId, Model model) {
        PriceDto priceDto = priceFacade.findPriceById(priceId);
        PackingDto packingDto = packingFacade.findPackingById(packingId);
        PricePackingDto pricePackingDto = new PricePackingDto();
        pricePackingDto.setPackingDto(packingDto);
        pricePackingDto.setPriceDto(priceDto);

        model.addAttribute("pricePacking", pricePackingDto);
        return "prices/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("pricePacking") PricePackingDto formBean, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "prices/update";
        }
        packingFacade.updatePacking(formBean.getPackingDto());
        priceFacade.updatePrice(formBean.getPriceDto());

        redirectAttributes.addFlashAttribute("alert_success", "Price " + formBean.getPriceDto().getPrice() + " " + formBean.getPriceDto().getCurrency() + " / " + formBean.getPackingDto().getVolume() + " l" + " was updated.");

        return "redirect:" + uriBuilder.path("/wines/update/" + formBean.getPackingDto().getWine().getId()).toUriString();
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