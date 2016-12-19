package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.UserRole;

/**
 * @author Silvia Borzová
 *         18/12/2016
 */
public class UserDto {

    private Long id;

    private String passwordHash;

    private String email;

    private String firstName;

    private String lastName;

    private UserRole userRole;

    public UserDto() {
        this.userRole = UserRole.MEMBER;
    }

    public UserDto(String passwordHash, String email, String firstName, String lastName, UserRole userRole) {
        this();
        this.passwordHash = passwordHash;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isAdmin() {
        return getUserRole() == UserRole.ADMIN;
    }

}
