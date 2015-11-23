package thecerealkillers.elearning.model;


/**
 * Created by Dani
 */
public class Topic {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic = (Topic) o;

        return title.equals(topic.title);

    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
