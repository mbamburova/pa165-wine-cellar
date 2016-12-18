package cz.muni.fi.pa165.facade;

import java.util.List;
import javax.inject.Inject;
import cz.muni.fi.pa165.dto.UserAuthDto;
import cz.muni.fi.pa165.dto.UserCreateDto;
import cz.muni.fi.pa165.dto.UserDto;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.enums.UserRole;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Silvia Borzová
 *         18/12/2016
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Inject
    private UserService userService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public Long registerUser(UserCreateDto user, String unencryptedPassword) {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        if (unencryptedPassword == null || unencryptedPassword.isEmpty()) {
            throw new IllegalArgumentException("Unencrypted password cannot be null nor empty.");
        }
        User userEntity = beanMappingService.mapTo(user, User.class);
        userService.registerUser(userEntity, unencryptedPassword);
        return userEntity.getId();
    }


    @Override
    public UserDto findUserById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("User id cannot be null.");
        }

        User user = userService.findUserById(id);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        return beanMappingService.mapTo(user, UserDto.class);
    }

    @Override
    public UserDto findUserByEmail(String email) {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null.");
        }

        User user = userService.findUserByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return beanMappingService.mapTo(user, UserDto.class);
    }

    @Override
    public UserRole userRole(UserDto user)
    {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        User userEntity = beanMappingService.mapTo(user, User.class);

        if (userService.findUserById(userEntity.getId()) == null) {
            throw new IllegalArgumentException("User not found during getting user role.");
        }

        return userService.userRole(userEntity);
    }

    @Override
    public boolean authenticateUser(UserAuthDto userAuth) {

        if (userAuth == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        User user = userService.findUserById(userAuth.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("User not found during authenticate.");
        }

        return userService.authenticateUser(user, userAuth.getPassword());
    }

    @Override
    public boolean isAdmin(UserDto u) {
        return userService.isAdmin(beanMappingService.mapTo(u, User.class));
    }

}
