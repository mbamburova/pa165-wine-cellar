package cz.muni.fi.pa165.dto.user;

import cz.muni.fi.pa165.enums.UserRole;

import java.util.Objects;

/**
 * @author Silvia Borzová
 *         18/12/2016
 */
public class UserCreateDto {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private UserRole userRole;

    public UserCreateDto() {
        this.userRole = UserRole.MEMBER;
    }

    public UserCreateDto(String email, String firstName, String lastName, UserRole userRole) {
        this();
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserDto)) return false;

        UserDto other = (UserDto) obj;

        return getEmail() != null ? this.getEmail().equals(other.getEmail()) : other.getEmail() == null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.getEmail());
        return hash;
    }

}
