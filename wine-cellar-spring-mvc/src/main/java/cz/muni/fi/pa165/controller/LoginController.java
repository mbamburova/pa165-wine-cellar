package cz.muni.fi.pa165.controller;

import javax.inject.Inject;
import cz.muni.fi.pa165.dto.UserAuthDto;
import cz.muni.fi.pa165.dto.UserDto;
import cz.muni.fi.pa165.facade.UserFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Silvia Borzová
 *         18/12/2016
 */

@Controller
public class LoginController extends LoggedUser{

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("user", new UserAuthDto());
        return "index";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(Model model) {
        return "error";
    }
}
