package cz.muni.fi.pa165.facade;

import java.util.List;
import cz.muni.fi.pa165.dto.UserAuthDto;
import cz.muni.fi.pa165.dto.UserCreateDto;
import cz.muni.fi.pa165.dto.UserDto;
import cz.muni.fi.pa165.enums.UserRole;

/**
 * @author Silvia Borzová
 *         18/12/2016
 */
public interface UserFacade {

    Long registerUser(UserCreateDto user, String unencryptedPassword);
    void updateUser(UserDto user);
    void deleteUser(Long id);
    UserDto findUserById(Long id);
    UserDto findUserByEmail(String email);
    List<UserDto> findAllUsers();
    UserRole userRole(UserDto user);
    boolean authenticateUser(UserAuthDto user);
    boolean isAdmin(UserDto u);
}
