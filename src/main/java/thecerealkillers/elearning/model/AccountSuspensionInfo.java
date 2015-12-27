package thecerealkillers.elearning.model;


/**
 * Created by Dani.
 */
public class AccountSuspensionInfo {

    private String accountUsername;

    public AccountSuspensionInfo() {
    }

    public AccountSuspensionInfo(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountSuspensionInfo that = (AccountSuspensionInfo) o;

        return accountUsername != null ? accountUsername.equals(that.accountUsername) : that.accountUsername == null;

    }

    @Override
    public int hashCode() {
        return accountUsername != null ? accountUsername.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AccountSuspensionInfo{" +
                "accountUsername='" + accountUsername + '\'' +
                '}';
    }
}
