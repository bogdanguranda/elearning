package thecerealkillers.elearning.model;

public class ForumThread {

    private String title;
    private String owner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "ForumThread{" +
                "title='" + title + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForumThread that = (ForumThread) o;

        if (!title.equals(that.title)) return false;
        return owner.equals(that.owner);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + owner.hashCode();
        return result;
    }
}
