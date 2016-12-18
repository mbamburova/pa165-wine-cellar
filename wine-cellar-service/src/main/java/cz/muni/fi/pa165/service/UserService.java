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
    User updateUser(User user);
    void deleteUser(User user);
    User findUserById(Long id);
    User findUserByEmail(String email);
    List<User> findAllUsers();
    UserRole userRole(User user);
    boolean authenticateUser(User user, String unencryptedPassword);
    public boolean isAdmin(User user);
}
