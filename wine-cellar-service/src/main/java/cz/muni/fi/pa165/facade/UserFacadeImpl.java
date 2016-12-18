package cz.muni.fi.pa165.facade;

import java.util.List;
import javax.inject.Inject;
import cz.muni.fi.pa165.dto.UserAuthDto;
import cz.muni.fi.pa165.dto.UserCreateDto;
import cz.muni.fi.pa165.dto.UserDto;
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
        return null;
    }

    @Override
    public void updateUser(UserDto user) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public UserDto findUserById(Long id) {
        return null;
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return null;
    }

    @Override
    public List<UserDto> findAllUsers() {
        return null;
    }

    @Override
    public UserRole userRole(UserDto user) {
        return null;
    }

    @Override
    public boolean authenticateUser(UserAuthDto user) {
        return false;
    }

    @Override
    public boolean isAdmin(UserDto u) {
        return false;
    }
}
