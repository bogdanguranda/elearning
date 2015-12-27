package thecerealkillers.elearning.model;


/**
 * Created by Dani
 */
public class ForumThread {

    private String title;
    private String topic;
    private String owner;

    public ForumThread(String title, String topic, String owner) {
        this.title = title;
        this.topic = topic;
        this.owner = owner;
    }

    public ForumThread() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForumThread that = (ForumThread) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;
        return owner != null ? owner.equals(that.owner) : that.owner == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ForumThread{" +
                "title='" + title + '\'' +
                ", topic='" + topic + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
