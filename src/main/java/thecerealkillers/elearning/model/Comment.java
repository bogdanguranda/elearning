package thecerealkillers.elearning.model;


import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Dani
 */
public class Comment {

    private Integer id;
    private String owner;
    private String topic;
    private String thread;
    private String message;
    private Date timeStamp;

    public Comment() {
    }

    public Comment(String owner, String topic, String thread, String message) {
        this.owner = owner;
        this.topic = topic;
        this.thread = thread;
        this.message = message;
    }

    /*
    public Comment(String owner, String topic, String thread, String message, Date timeStamp) {
        this.owner = owner;
        this.topic = topic;
        this.thread = thread;
        this.message = message;
        this.timeStamp = timeStamp;
    }
    */

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != null ? !id.equals(comment.id) : comment.id != null) return false;
        if (owner != null ? !owner.equals(comment.owner) : comment.owner != null) return false;
        if (topic != null ? !topic.equals(comment.topic) : comment.topic != null) return false;
        if (thread != null ? !thread.equals(comment.thread) : comment.thread != null) return false;
        if (message != null ? !message.equals(comment.message) : comment.message != null) return false;
        return timeStamp != null ? timeStamp.equals(comment.timeStamp) : comment.timeStamp == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (thread != null ? thread.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStampStr = sdf.format(timeStamp);

        return "Comment{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", topic='" + topic + '\'' +
                ", thread='" + thread + '\'' +
                ", message='" + message + '\'' +
                ", timeStamp=" + timeStampStr +
                '}';
    }

    public String toString2() {
        return "Comment{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", topic='" + topic + '\'' +
                ", thread='" + thread + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
