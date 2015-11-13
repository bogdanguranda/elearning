package thecerealkillers.elearning.utilities;

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
}
