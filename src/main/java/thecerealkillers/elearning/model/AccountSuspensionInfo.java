package thecerealkillers.elearning.model;

/**
 * Created by Dani.
 */
public class AccountSuspensionInfo {

    private String accountUsername;
    private Boolean accountStatus;

    public AccountSuspensionInfo(String accountUsername, Boolean accountStatus) {
        this.accountUsername = accountUsername;
        this.accountStatus = accountStatus;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public Boolean getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountSuspensionInfo that = (AccountSuspensionInfo) o;

        if (accountUsername != null ? !accountUsername.equals(that.accountUsername) : that.accountUsername != null)
            return false;
        return !(accountStatus != null ? !accountStatus.equals(that.accountStatus) : that.accountStatus != null);

    }

    @Override
    public int hashCode() {
        int result = accountUsername != null ? accountUsername.hashCode() : 0;
        result = 31 * result + (accountStatus != null ? accountStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountSuspensionInfo{" +
                "accountUsername='" + accountUsername + '\'' +
                ", accountStatus=" + accountStatus +
                '}';
    }
}
