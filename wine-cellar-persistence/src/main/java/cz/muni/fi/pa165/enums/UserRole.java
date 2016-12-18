package cz.muni.fi.pa165.enums;

/**
 * @author Silvia Borzová
 *         18/12/2016
 */
public enum UserRole {

    ADMIN("Admin"),

    MEMBER("Member");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
