package cz.muni.fi.pa165.config;

import cz.muni.fi.pa165.dto.user.UserAuthDto;
import cz.muni.fi.pa165.dto.user.UserDto;
import cz.muni.fi.pa165.exceptions.NotFoundException;
import cz.muni.fi.pa165.facade.UserFacade;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Silvia Borzová
 *         18/12/2016
 */

@Component
public class AuthenticationConfig implements AuthenticationProvider {

    @Inject
    private UserFacade userFacade;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDto user = null;

        try {
            user = userFacade.findUserByEmail(email);
        } catch (NotFoundException | IllegalArgumentException e) {
            return null;
        }

        UserAuthDto authData = new UserAuthDto(user.getId(), password);

        if (userFacade.authenticateUser(authData)) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            if (userFacade.isAdmin(user)) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return new UsernamePasswordAuthenticationToken(email, password, grantedAuthorities);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authClass) {
        return authClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
