package thecerealkillers.elearning.model;

import java.util.List;

/**
 * Created with love by Lucian and @Pi on 29.12.2015.
 */
public class OnlineTest {

    private String title;
    private String course;
    private String attempts;
    private List<Question> questions;

    @Override
    public String toString() {
        return "OnlineTest{" +
                "title='" + title + '\'' +
                ", course='" + course + '\'' +
                ", attempts='" + attempts + '\'' +
                ", questions=" + questions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OnlineTest that = (OnlineTest) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (course != null ? !course.equals(that.course) : that.course != null) return false;
        if (attempts != null ? !attempts.equals(that.attempts) : that.attempts != null) return false;
        return questions != null ? questions.equals(that.questions) : that.questions == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (attempts != null ? attempts.hashCode() : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        return result;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAttempts() {
        return attempts;
    }

    public void setAttempts(String attempts) {
        this.attempts = attempts;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public OnlineTest() {

    }

    public OnlineTest(String title, String course, String attempts, List<Question> questions) {

        this.title = title;
        this.course = course;
        this.attempts = attempts;
        this.questions = questions;
    }
}
