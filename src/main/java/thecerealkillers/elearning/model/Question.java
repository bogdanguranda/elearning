package thecerealkillers.elearning.model;

/**
 * Created with love by Lucian and @Pi on 29.12.2015.
 */
public class Question {

    private int questionID;
    private String courseTitle;
    private String testTitle;
    private String questionText;

    @Override
    public String toString() {
        return "Question{" +
                "questionID=" + questionID +
                ", courseTitle='" + courseTitle + '\'' +
                ", testTitle='" + testTitle + '\'' +
                ", questionText='" + questionText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (questionID != question.questionID) return false;
        if (courseTitle != null ? !courseTitle.equals(question.courseTitle) : question.courseTitle != null)
            return false;
        if (testTitle != null ? !testTitle.equals(question.testTitle) : question.testTitle != null) return false;
        return questionText != null ? questionText.equals(question.questionText) : question.questionText == null;

    }

    @Override
    public int hashCode() {
        int result = questionID;
        result = 31 * result + (courseTitle != null ? courseTitle.hashCode() : 0);
        result = 31 * result + (testTitle != null ? testTitle.hashCode() : 0);
        result = 31 * result + (questionText != null ? questionText.hashCode() : 0);
        return result;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Question(int questionID, String courseTitle, String testTitle, String questionText) {

        this.questionID = questionID;
        this.courseTitle = courseTitle;
        this.testTitle = testTitle;
        this.questionText = questionText;
    }
}
