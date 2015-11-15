package thecerealkillers.elearning.model;

import java.util.Date;

/**
 * Created by cuvidk on 11/15/2015.
 */
public class UserStatus {
    private String username;
    private Boolean active;
    private Date signUpTimestamp;

    public UserStatus() {
    }

    public UserStatus(String username, Boolean status, Date signUpTimestamp) {
        this.username = username;
        this.active = status;
        this.signUpTimestamp = signUpTimestamp;
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

    @Override
    public String toString() {
        return "UserStatus{" +
                "username='" + username + '\'' +
                ", status=" + active +
                ", signUpTimestamp=" + signUpTimestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStatus)) return false;

        UserStatus that = (UserStatus) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        return !(signUpTimestamp != null ? !signUpTimestamp.equals(that.signUpTimestamp) : that.signUpTimestamp != null);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (signUpTimestamp != null ? signUpTimestamp.hashCode() : 0);
        return result;
    }
}
