package thecerealkillers.elearning.model;

/**
 * Created with love by Lucian and @Pi on 14.12.2015.
 */
public class AuthenticationInfo {
    private String token;
    private String role;

    @Override
    public String toString() {
        return "AuthenticationInfo{" +
                "token='" + token + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthenticationInfo that = (AuthenticationInfo) o;

        return !(token != null ? !token.equals(that.token) : that.token != null) && !(role != null ? !role.equals(that.role) : that.role != null);

    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
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

    public AuthenticationInfo(String token, String role) {

        this.token = token;
        this.role = role;
    }
}
