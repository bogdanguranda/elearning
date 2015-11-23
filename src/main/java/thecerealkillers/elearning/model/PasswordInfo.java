package thecerealkillers.elearning.model;


/**
 * Created by Dani
 */
public class PasswordInfo {

    private String password;
    private String hash;
    private String salt;

    public PasswordInfo(String accountPassword) {
        password = accountPassword;
        hash = "";
        salt = "";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "PasswordInfo{" +
                "password='" + password + '\'' +
                ", hash='" + hash + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordInfo that = (PasswordInfo) o;

        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (hash != null ? !hash.equals(that.hash) : that.hash != null) return false;
        return !(salt != null ? !salt.equals(that.salt) : that.salt != null);

    }

    @Override
    public int hashCode() {
        int result = password != null ? password.hashCode() : 0;
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        return result;
    }
}
