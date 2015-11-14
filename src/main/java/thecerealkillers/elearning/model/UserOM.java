package thecerealkillers.elearning.model;

/**
 * Created by cuvidk on 11/14/2015.
 */
public class UserOM {
    private String username;
    private String password;

    public UserOM() {
        this.username = "";
        this.password = "";
    }

    public UserOM(String username, String password) {
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
        return "UserOM{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserOM)) return false;

        UserOM userOM = (UserOM) o;

        if (username != null ? !username.equals(userOM.username) : userOM.username != null) return false;
        return !(password != null ? !password.equals(userOM.password) : userOM.password != null);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
