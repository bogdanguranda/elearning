package thecerealkillers.elearning.model;

/**
 * Created with love by Lucian and @Pi on 14.12.2015.
 * Changed with HATE by Bogdan on 24.12.2015.
 */
public class AuthenticationInfo {
    private String token;
    private String role;
    private String username;

    public AuthenticationInfo(String token, String role, String username) {
        this.token = token;
        this.role = role;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthenticationInfo that = (AuthenticationInfo) o;

        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return !(username != null ? !username.equals(that.username) : that.username != null);

    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AuthenticationInfo{" +
                "token='" + token + '\'' +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
