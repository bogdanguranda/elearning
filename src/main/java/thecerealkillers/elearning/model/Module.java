package thecerealkillers.elearning.model;

/**
 * Created by cuvidk on 12/22/2015.
 */
public class Module {
    private String title;
    private String course;
    private String description;

    public Module() {}

    public Module(String title, String course, String description) {
        this.title = title;
        this.course = course;
        this.description = description;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module)) return false;

        Module module = (Module) o;

        if (title != null ? !title.equals(module.title) : module.title != null) return false;
        if (course != null ? !course.equals(module.course) : module.course != null) return false;
        return !(description != null ? !description.equals(module.description) : module.description != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Module{" +
                "title='" + title + '\'' +
                ", course='" + course + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
