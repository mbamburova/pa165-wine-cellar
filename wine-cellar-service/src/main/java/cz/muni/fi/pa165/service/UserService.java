package cz.muni.fi.pa165.service;

import java.util.List;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.enums.UserRole;

/**
 * @author Silvia Borzová
 *         18/12/2016
 */
public interface UserService {

    void registerUser(User user, String unencryptedPassword);
    User findUserById(Long id);
    User findUserByEmail(String email);
    UserRole userRole(User user);
    boolean authenticateUser(User user, String unencryptedPassword);
    boolean isAdmin(User user);
}
