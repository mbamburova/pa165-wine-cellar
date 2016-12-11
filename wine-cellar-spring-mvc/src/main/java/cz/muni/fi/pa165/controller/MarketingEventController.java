package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.PackingCreateDto;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.12.11
 */
@Component
@Controller
@RequestMapping("/marketingevents")
public class MarketingEventController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {

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

        model.addAttribute("marketingevents", marketingEvents);


        return "marketingevents/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCategory(Model model) {
        model.addAttribute("marketingEventCreate", new MarketingEventDto());
        return "marketingevents/new";
    }

}
