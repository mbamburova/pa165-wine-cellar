package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.UserAuthDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Silvia Borzová
 *         18/12/2016
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("user", new UserAuthDto());
        return "login/index";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(Model model) {
        return "error";
    }
}