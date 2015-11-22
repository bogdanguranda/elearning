package thecerealkillers.elearning.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {

    private String owner;
    private Date timeStamp;
    private String message;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Comment() {
        message = "";
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStampStr = sdf.format(timeStamp);

        return "Comment{" +
                "owner='" + owner + '\'' +
                ", timeStamp=" + timeStampStr +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (!owner.equals(comment.owner)) return false;
        if (!timeStamp.equals(comment.timeStamp)) return false;
        return message.equals(comment.message);

    }

    @Override
    public int hashCode() {
        int result = owner.hashCode();
        result = 31 * result + timeStamp.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }
}
