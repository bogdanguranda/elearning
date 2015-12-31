package thecerealkillers.elearning.model;

/**
 * Created with love by Lucian and @Pi on 29.12.2015.
 */
public class Question {

    private String text;
    private String answer1;
    private String correct1;
    private String answer2;
    private String correct2;
    private String answer3;
    private String correct3;
    private String answer4;
    private String correct4;

    public Question() {
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", correct1='" + correct1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", correct2='" + correct2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", correct3='" + correct3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                ", correct4='" + correct4 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (text != null ? !text.equals(question.text) : question.text != null)
            return false;
        if (answer1 != null ? !answer1.equals(question.answer1) : question.answer1 != null) return false;
        if (correct1 != null ? !correct1.equals(question.correct1) : question.correct1 != null) return false;
        if (answer2 != null ? !answer2.equals(question.answer2) : question.answer2 != null) return false;
        if (correct2 != null ? !correct2.equals(question.correct2) : question.correct2 != null) return false;
        if (answer3 != null ? !answer3.equals(question.answer3) : question.answer3 != null) return false;
        if (correct3 != null ? !correct3.equals(question.correct3) : question.correct3 != null) return false;
        if (answer4 != null ? !answer4.equals(question.answer4) : question.answer4 != null) return false;
        return correct4 != null ? correct4.equals(question.correct4) : question.correct4 == null;

    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (answer1 != null ? answer1.hashCode() : 0);
        result = 31 * result + (correct1 != null ? correct1.hashCode() : 0);
        result = 31 * result + (answer2 != null ? answer2.hashCode() : 0);
        result = 31 * result + (correct2 != null ? correct2.hashCode() : 0);
        result = 31 * result + (answer3 != null ? answer3.hashCode() : 0);
        result = 31 * result + (correct3 != null ? correct3.hashCode() : 0);
        result = 31 * result + (answer4 != null ? answer4.hashCode() : 0);
        result = 31 * result + (correct4 != null ? correct4.hashCode() : 0);
        return result;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getCorrect1() {
        return correct1;
    }

    public void setCorrect1(String correct1) {
        this.correct1 = correct1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getCorrect2() {
        return correct2;
    }

    public void setCorrect2(String correct2) {
        this.correct2 = correct2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getCorrect3() {
        return correct3;
    }

    public void setCorrect3(String correct3) {
        this.correct3 = correct3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getCorrect4() {
        return correct4;
    }

    public void setCorrect4(String correct4) {
        this.correct4 = correct4;
    }

    public Question(String text, String answer1, String correct1, String answer2, String correct2, String answer3, String correct3, String answer4, String correct4) {

        this.text = text;
        this.answer1 = answer1;
        this.correct1 = correct1;
        this.answer2 = answer2;
        this.correct2 = correct2;
        this.answer3 = answer3;
        this.correct3 = correct3;
        this.answer4 = answer4;
        this.correct4 = correct4;
    }
}
