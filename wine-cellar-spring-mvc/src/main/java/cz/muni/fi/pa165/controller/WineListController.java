package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.MarketingEventDto;
import cz.muni.fi.pa165.dto.WineDto;
import cz.muni.fi.pa165.dto.WineListDto;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Gordian on 11/6/2016.
 */
@Controller
@RequestMapping("/winelists")
public class WineListController {

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

        WineDto wineDto = new WineDto();
        wineDto.setId(1L);
        wineDto.setDescription("rgrhrh");
        wineDto.setAcidity(new BigDecimal(1));
        wineDto.setAlcoholVolume(new BigDecimal(2));
        wineDto.setGrapeSugarContent(new BigDecimal(3));
        wineDto.setBatch("hovno");
        wineDto.setPredicate("hovno");
        wineDto.setName("hovefef");
        wineDto.setVintage(12);
        wineDto.setResidualSugar(new BigDecimal(45));
        wineDto.setNotes("joejfie");
        wineDto.setPredicateEquivalent("efefef");

        List<WineListDto> wineLists = new ArrayList<>();
        WineListDto wineList = new WineListDto();
        wineList.addWine(wineDto);
        wineList.setName("Hovno");
        wineList.setDate(new LocalDateTime(2016,12,1,0,0));
        wineList.setMarketingEvent(marketingEvent1);

        wineLists.add(wineList);

        model.addAttribute("winelists", wineLists);

        return "winelists/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newWineList(Model model) {
        model.addAttribute("wineListCreate", new WineListDto());
        return "winelists/new";
    }

    @ModelAttribute("wines")
    public List<WineDto> wines() {

        List<WineDto> wines = new ArrayList<>();

        WineDto wineDto1 = new WineDto();
        wineDto1.setId(1L);
        wineDto1.setDescription("rgrhrh");
        wineDto1.setAcidity(new BigDecimal(1));
        wineDto1.setAlcoholVolume(new BigDecimal(2));
        wineDto1.setGrapeSugarContent(new BigDecimal(3));
        wineDto1.setBatch("hovno");
        wineDto1.setPredicate("hovno");
        wineDto1.setName("hovefef");
        wineDto1.setVintage(12);
        wineDto1.setResidualSugar(new BigDecimal(45));
        wineDto1.setNotes("joejfie");
        wineDto1.setPredicateEquivalent("efefef");
        wines.add(wineDto1);
        WineDto wineDto2 = new WineDto();
        wineDto2.setId(2L);
        wineDto2.setDescription("deskripcia");
        wineDto2.setAcidity(new BigDecimal(1));
        wineDto2.setAlcoholVolume(new BigDecimal(2));
        wineDto2.setGrapeSugarContent(new BigDecimal(3));
        wineDto2.setBatch("hovno");
        wineDto2.setPredicate("hovno");
        wineDto2.setName("finlandia");
        wineDto2.setVintage(12);
        wineDto2.setResidualSugar(new BigDecimal(45));
        wineDto2.setNotes("brusnica");
        wineDto2.setPredicateEquivalent("efefef");
        wines.add(wineDto2);

        return wines;
    }
}
