package cz.muni.fi.pa165.controller;

import javax.inject.Inject;
import cz.muni.fi.pa165.dto.UserDto;
import cz.muni.fi.pa165.facade.UserFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Silvia Borzová
 *         19/12/2016
 */
public class LoggedUser {

    @Inject
    private UserFacade userFacade;

    @ModelAttribute("loggedUser")
    protected UserDto getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        String email = authentication.getName();
        if (email == null) {
            return null;
        }
        return  userFacade.findUserByEmail(email);
    }
}
