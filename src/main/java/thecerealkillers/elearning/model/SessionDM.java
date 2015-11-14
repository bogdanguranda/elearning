package thecerealkillers.elearning.model;

import java.util.Date;

/**
 * Created by cuvidk on 11/14/2015.
 */
public class SessionDM {

    private String username;
    private String token;
    private Date creationStamp;

    public SessionDM() {
    }

    public SessionDM(String username, String token, Date creationStamp) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreationStamp() {
        return creationStamp;
    }

    public void setCreationStamp(Date creationStamp) {
        this.creationStamp = creationStamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SessionDM{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", creationStamp=" + creationStamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionDM)) return false;

        SessionDM sessionDM = (SessionDM) o;

        if (username != null ? !username.equals(sessionDM.username) : sessionDM.username != null) return false;
        if (token != null ? !token.equals(sessionDM.token) : sessionDM.token != null) return false;
        return !(creationStamp != null ? !creationStamp.equals(sessionDM.creationStamp) : sessionDM.creationStamp != null);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (creationStamp != null ? creationStamp.hashCode() : 0);
        return result;
    }
}
