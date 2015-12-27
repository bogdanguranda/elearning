package thecerealkillers.elearning.model;


/**
 * Created by Dani
 */
public class Topic {

    private String title;

    public Topic() {
    }

    public Topic(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic = (Topic) o;

        return title != null ? title.equals(topic.title) : topic.title == null;

    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "title='" + title + '\'' +
                '}';
    }
}
