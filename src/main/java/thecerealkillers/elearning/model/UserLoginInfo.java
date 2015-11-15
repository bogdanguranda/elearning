package thecerealkillers.elearning.model;

/**
 * Created by cuvidk on 11/14/2015.
 */
public class UserLoginInfo {
    private String username;
    private String password;

    public UserLoginInfo() {
    }

    public UserLoginInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserLoginInfo)) return false;

        UserLoginInfo userLoginInfo = (UserLoginInfo) o;

        if (username != null ? !username.equals(userLoginInfo.username) : userLoginInfo.username != null) return false;
        return !(password != null ? !password.equals(userLoginInfo.password) : userLoginInfo.password != null);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
