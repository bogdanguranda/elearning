package thecerealkillers.elearning.model;

public class Course {

    private String title;
    private String about;
    private String recommendedBackground;
    private String suggestedReadings;
    private String courseFormat;

    public Course() {

    }

    public Course(String title, String about, String recommendedBackground, String suggestedReadings, String courseFormat) {
        this.title = title;
        this.about = about;
        this.recommendedBackground = recommendedBackground;
        this.suggestedReadings = suggestedReadings;
        this.courseFormat = courseFormat;
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

    public String getRecommendedBackground() {
        return recommendedBackground;
    }

    public void setRecommendedBackground(String recommendedBackground) {
        this.recommendedBackground = recommendedBackground;
    }

    public String getSuggestedReadings() {
        return suggestedReadings;
    }

    public void setSuggestedReadings(String suggestedReadings) {
        this.suggestedReadings = suggestedReadings;
    }

    public String getCourseFormat() {
        return courseFormat;
    }

    public void setCourseFormat(String courseFormat) {
        this.courseFormat = courseFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (title != null ? !title.equals(course.title) : course.title != null) return false;
        if (about != null ? !about.equals(course.about) : course.about != null) return false;
        if (recommendedBackground != null ? !recommendedBackground.equals(course.recommendedBackground) : course.recommendedBackground != null)
            return false;
        if (suggestedReadings != null ? !suggestedReadings.equals(course.suggestedReadings) : course.suggestedReadings != null)
            return false;
        return !(courseFormat != null ? !courseFormat.equals(course.courseFormat) : course.courseFormat != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (about != null ? about.hashCode() : 0);
        result = 31 * result + (recommendedBackground != null ? recommendedBackground.hashCode() : 0);
        result = 31 * result + (suggestedReadings != null ? suggestedReadings.hashCode() : 0);
        result = 31 * result + (courseFormat != null ? courseFormat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", about='" + about + '\'' +
                ", recommendedBackground='" + recommendedBackground + '\'' +
                ", suggestedReadings='" + suggestedReadings + '\'' +
                ", courseFormat='" + courseFormat + '\'' +
                '}';
    }
}
