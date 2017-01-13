package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.user.UserAuthDto;
import cz.muni.fi.pa165.dto.user.UserCreateDto;
import cz.muni.fi.pa165.dto.user.UserDto;
import cz.muni.fi.pa165.enums.UserRole;

/**
 * @author Silvia Borzová
 *         18/12/2016
 */
public interface UserFacade {

    Long registerUser(UserCreateDto user, String unencryptedPassword);

    UserDto findUserById(Long id);

    UserDto findUserByEmail(String email);

    UserRole userRole(UserDto user);

    boolean authenticateUser(UserAuthDto user);

    boolean isAdmin(UserDto u);
}
