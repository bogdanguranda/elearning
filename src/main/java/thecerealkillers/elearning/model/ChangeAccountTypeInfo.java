package thecerealkillers.elearning.model;


/**
 * Created by Dani.
 */
public class ChangeAccountTypeInfo {
    private String accountUsername;
    private String newAccountType;

    public ChangeAccountTypeInfo() {
    }

    public ChangeAccountTypeInfo(String accountUsername, String newAccountType) {
        this.accountUsername = accountUsername;
        this.newAccountType = newAccountType;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public String getNewAccountType() {
        return newAccountType;
    }

    public void setNewAccountType(String newAccountType) {
        this.newAccountType = newAccountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangeAccountTypeInfo that = (ChangeAccountTypeInfo) o;

        if (accountUsername != null ? !accountUsername.equals(that.accountUsername) : that.accountUsername != null)
            return false;
        return !(newAccountType != null ? !newAccountType.equals(that.newAccountType) : that.newAccountType != null);

    }

    @Override
    public int hashCode() {
        int result = accountUsername != null ? accountUsername.hashCode() : 0;
        result = 31 * result + (newAccountType != null ? newAccountType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ChangeAccountTypeInfo{" +
                "accountUsername='" + accountUsername + '\'' +
                ", newAccountType='" + newAccountType + '\'' +
                '}';
    }
}
