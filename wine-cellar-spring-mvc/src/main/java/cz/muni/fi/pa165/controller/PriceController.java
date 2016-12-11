package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.PackingCreateDto;
import cz.muni.fi.pa165.dto.PriceCreateDto;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {

        List<PriceCreateDto> prices = new ArrayList<>();

        PriceCreateDto price1 = new PriceCreateDto();
        PriceCreateDto price2 = new PriceCreateDto();

        price1.setId(1L);
        price2.setId(2L);

        price1.setPrice(new BigDecimal(90));
        price2.setPrice(new BigDecimal(110));

        price1.setCurrency(Currency.getInstance("CZK"));
        price2.setCurrency(Currency.getInstance("CZK"));

        prices.add(price1);
        prices.add(price2);

        model.addAttribute("prices", prices);


        return "prices/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCategory(Model model) {
        model.addAttribute("priceCreate", new PriceCreateDto());
        return "prices/new";
    }

    @ModelAttribute("currencies")
    public List<String> currencies() {
        return Arrays.asList("CZK", "EUR", "USD");
    }

    @ModelAttribute("marketingevents")
    public List<MarketingEventDto> marketingEvent() {

        List<MarketingEventDto> marketingEvents = new ArrayList<>();

        MarketingEventDto marketingEvent1 = new MarketingEventDto();
        MarketingEventDto marketingEvent2 = new MarketingEventDto();
        MarketingEventDto marketingEvent3 = new MarketingEventDto();

        marketingEvent1.setId(1L);
        marketingEvent2.setId(2L);
        marketingEvent3.setId(3L);

        marketingEvent1.setDescription("chlastačka");
        marketingEvent2.setDescription("narodky");
        marketingEvent3.setDescription("silvester");

        marketingEvents.add(marketingEvent1);
        marketingEvents.add(marketingEvent2);
        marketingEvents.add(marketingEvent3);

        return marketingEvents;
    }

    @ModelAttribute("packings")
    public List<PackingCreateDto> wines() {

        List<PackingCreateDto> packings = new ArrayList<>();

        PackingCreateDto packingCreateDto = new PackingCreateDto();
        packingCreateDto.setId(1L);
        packingCreateDto.setWineId(1L);
        packingCreateDto.setVolume(new BigDecimal(0.35));
        packingCreateDto.setValidFrom(new LocalDateTime(2016,12,1,0,0));
        packingCreateDto.setValidTo(new LocalDateTime(2016,12,31,0,0));

        packings.add(packingCreateDto);

        return packings;
    }
}