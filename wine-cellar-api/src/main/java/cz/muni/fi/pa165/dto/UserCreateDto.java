package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.UserRole;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCreateDto)) return false;

        UserCreateDto user = (UserCreateDto) o;

        return getId() != null && getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

}
