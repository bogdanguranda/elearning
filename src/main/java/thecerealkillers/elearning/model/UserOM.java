package thecerealkillers.elearning.model;

/**
 * Created by cuvidk on 11/14/2015.
 */
public class UserOM {
    private String username;
    private String password;
    private String token;

    public UserOM() {
        this.username = "";
        this.password = "";
        this.token = "";
    }

    public UserOM(String username, String password, String token) {
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public UserOM(String username, String password) {
        this.username = username;
        this.password = password;
        this.token = "";
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserOM{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserOM)) return false;

        UserOM userOM = (UserOM) o;

        if (username != null ? !username.equals(userOM.username) : userOM.username != null) return false;
        if (password != null ? !password.equals(userOM.password) : userOM.password != null) return false;
        return !(token != null ? !token.equals(userOM.token) : userOM.token != null);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}
