package thecerealkillers.elearning.model;

/**
 * Created by Dani.
 */
public class ForumThreadIdentifier {

    private String title;
    private String topic;

    public ForumThreadIdentifier() {
    }

    public ForumThreadIdentifier(String title, String topic) {
        this.title = title;
        this.topic = topic;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForumThreadIdentifier that = (ForumThreadIdentifier) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return topic != null ? topic.equals(that.topic) : that.topic == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ForumThreadIdentifier{" +
                "title='" + title + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
