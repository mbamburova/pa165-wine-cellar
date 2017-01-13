package cz.muni.fi.pa165.dto.user;

/**
 * @author Silvia Borzová
 *         18/12/2016
 */
public class UserAuthDto {

    private Long userId;

    private String password;

    public UserAuthDto() {
        this.userId = null;
        this.password = null;
    }

    public UserAuthDto(Long userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
