package cz.muni.fi.pa165.dao;

import java.util.List;
import cz.muni.fi.pa165.entity.User;

/**
 * @author Silvia Borzová
 *         18/12/2016
 */
public interface UserDao {

    /**
     * Creates new user in the database
     * @param user
     */
    void createUser(User user);

    /**
     * Updates the given user in the database
     * @param user
     * @return merged User entity
     */
    User updateUser(User user);

    /**
     * Deletes the given user from the database
     * @param user to be deleted
     */
    void deleteUser(User user);

    /**
     * Finds a user with the given id in the database.
     * @param id of  user
     * @return user
     */
    User findUserById(Long id);

    /**
     * Finds a user with the given email in the database.
     * @param email of the user
     * @return user
     */
    User findUserByEmail(String email);

    /**
     * Finds all users in the database.
     * @return users
     */
    List<User> findAllUsers();

}
