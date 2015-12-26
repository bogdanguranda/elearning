package thecerealkillers.elearning.model;


import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Dani.
 */
public class AuditItem {

    private Integer id;
    private String username;
    private String operationName;
    private String dataReceived;
    private String response;
    private Boolean success;
    private Date timestamp;

    public AuditItem() {
    }

    public AuditItem(String username, String operationName, String dataReceived, String response, Boolean success, Date timestamp) {
        this.username = username;
        this.operationName = operationName;
        this.dataReceived = dataReceived;
        this.response = response;
        this.success = success;
        this.timestamp = timestamp;
    }

    public AuditItem(String username, String operationName, String dataReceived, String response, Boolean success) {
        this.username = username;
        this.operationName = operationName;
        this.dataReceived = dataReceived;
        this.response = response;
        this.success = success;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getDataReceived() {
        return dataReceived;
    }

    public void setDataReceived(String dataReceived) {
        this.dataReceived = dataReceived;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditItem auditItem = (AuditItem) o;

        if (id != null ? !id.equals(auditItem.id) : auditItem.id != null) return false;
        if (username != null ? !username.equals(auditItem.username) : auditItem.username != null) return false;
        if (operationName != null ? !operationName.equals(auditItem.operationName) : auditItem.operationName != null)
            return false;
        if (dataReceived != null ? !dataReceived.equals(auditItem.dataReceived) : auditItem.dataReceived != null)
            return false;
        if (response != null ? !response.equals(auditItem.response) : auditItem.response != null) return false;
        if (success != null ? !success.equals(auditItem.success) : auditItem.success != null) return false;
        return timestamp != null ? timestamp.equals(auditItem.timestamp) : auditItem.timestamp == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (operationName != null ? operationName.hashCode() : 0);
        result = 31 * result + (dataReceived != null ? dataReceived.hashCode() : 0);
        result = 31 * result + (response != null ? response.hashCode() : 0);
        result = 31 * result + (success != null ? success.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStampStr = sdf.format(timestamp);

        return "AuditItem{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", operationName='" + operationName + '\'' +
                ", dataReceived='" + dataReceived + '\'' +
                ", response='" + response + '\'' +
                ", success=" + success +
                ", timestamp=" + timeStampStr +
                '}';
    }
}
