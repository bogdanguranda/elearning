package thecerealkillers.elearning.model;

/**
 * Created with love by Lucian and @Pi on 29.12.2015.
 */
public class OnlineTest {

    private String testTitle;
    private String courseTitle;
    private String numberOfTries;

    public OnlineTest() {
        
    }

    @Override
    public String toString() {
        return "OnlineTest{" +
                "testTitle='" + testTitle + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", numberOfTries=" + numberOfTries +
                '}';
    }

    public OnlineTest(String testTitle, String courseTitle, String numberOfTries) {
        this.testTitle = testTitle;
        this.courseTitle = courseTitle;
        this.numberOfTries = numberOfTries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OnlineTest that = (OnlineTest) o;

        if (numberOfTries != that.numberOfTries) return false;
        if (testTitle != null ? !testTitle.equals(that.testTitle) : that.testTitle != null) return false;
        return courseTitle != null ? courseTitle.equals(that.courseTitle) : that.courseTitle == null;

    }

    @Override
    public int hashCode() {
        int result = testTitle != null ? testTitle.hashCode() : 0;
        result = 31 * result + (courseTitle != null ? courseTitle.hashCode() : 0);
        result = 31 * result + (numberOfTries != null ? numberOfTries.hashCode() : 0);
        return result;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getNumberOfTries() {
        return numberOfTries;
    }

    public void setNumberOfTries(String numberOfTries) {
        this.numberOfTries = numberOfTries;
    }


}
