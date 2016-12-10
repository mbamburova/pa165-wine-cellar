package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.WineDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MarekScholtz on 10.12.2016.
 */
@Controller
@RequestMapping("/wines")
public class WineController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {

        List<WineDto> wines = new ArrayList<>();

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

        wines.add(wineDto);

        model.addAttribute("wines", wines);


        return "wines/index";
    }

}
