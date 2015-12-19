package thecerealkillers.elearning.model;

import java.util.Date;

/**
 * Created by Lucian on 09.11.2015.
 * Modified by Dani
 */
public class Message {

    private String senderUsername;
    private String receiverUsername;
    private Date timestamp;
    private String message;

    public Message(String senderUsername, String receiverUsername, String message) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.message = message;
    }

    public Message() {

    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (senderUsername != null ? !senderUsername.equals(message1.senderUsername) : message1.senderUsername != null)
            return false;
        if (receiverUsername != null ? !receiverUsername.equals(message1.receiverUsername) : message1.receiverUsername != null)
            return false;
        if (timestamp != null ? !timestamp.equals(message1.timestamp) : message1.timestamp != null) return false;
        return !(message != null ? !message.equals(message1.message) : message1.message != null);

    }

    @Override
    public int hashCode() {
        int result = senderUsername != null ? senderUsername.hashCode() : 0;
        result = 31 * result + (receiverUsername != null ? receiverUsername.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "senderUsername='" + senderUsername + '\'' +
                ", receiverUsername='" + receiverUsername + '\'' +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }
}
