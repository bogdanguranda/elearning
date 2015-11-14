package thecerealkillers.elearning.model;

public class Course {

    private String title;
    private String about;
    private String details;
    private String owner;

    public Course(String title, String about, String details, String owner) {
        this.title = title;
        this.about = about;
        this.details = details;
        this.owner = owner;
    }

    public Course() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

        Course course = (Course) o;

        if (title != null ? !title.equals(course.title) : course.title != null) return false;
        if (about != null ? !about.equals(course.about) : course.about != null) return false;
        if (details != null ? !details.equals(course.details) : course.details != null) return false;
        return !(owner != null ? !owner.equals(course.owner) : course.owner != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (about != null ? about.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", about='" + about + '\'' +
                ", details='" + details + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
