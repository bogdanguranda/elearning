package thecerealkillers.elearning.model;


/**
 * Created by Dani.
 */
public class Permission {
    private String operationName;
    private String roleName;
    private Boolean permission;

    public Permission() {
    }

    public Permission(String operationName, String roleName, Boolean permission) {
        this.operationName = operationName;
        this.roleName = roleName;
        this.permission = permission;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getPermission() {
        return permission;
    }

    public void setPermission(Boolean permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        if (operationName != null ? !operationName.equals(that.operationName) : that.operationName != null)
            return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;
        return permission != null ? permission.equals(that.permission) : that.permission == null;

    }

    @Override
    public int hashCode() {
        int result = operationName != null ? operationName.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "operationName='" + operationName + '\'' +
                ", roleName='" + roleName + '\'' +
                ", permission=" + permission +
                '}';
    }
}
