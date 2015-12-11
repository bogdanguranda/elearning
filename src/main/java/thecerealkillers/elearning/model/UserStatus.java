package thecerealkillers.elearning.model;

import java.util.Date;


/**
 * Created by cuvidk on 11/15/2015.
 * Modified by Dani
 */
public class UserStatus {
    private String username;
    private Boolean active;
    private Date signUpTimestamp;
    private String token;

    public UserStatus() {
    }

    public UserStatus(String username, String token) {
        this.username = username;
        this.active = false;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getSignUpTimestamp() {
        return signUpTimestamp;
    }

    public void setSignUpTimestamp(Date signUpTimestamp) {
        this.signUpTimestamp = signUpTimestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserStatus that = (UserStatus) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (signUpTimestamp != null ? !signUpTimestamp.equals(that.signUpTimestamp) : that.signUpTimestamp != null)
            return false;
        return !(token != null ? !token.equals(that.token) : that.token != null);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (signUpTimestamp != null ? signUpTimestamp.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "username='" + username + '\'' +
                ", active=" + active +
                ", signUpTimestamp=" + signUpTimestamp +
                ", token='" + token + '\'' +
                '}';
    }
}
