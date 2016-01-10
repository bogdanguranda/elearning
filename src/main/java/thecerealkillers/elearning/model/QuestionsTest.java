package thecerealkillers.elearning.model;

/**
 * Created with love by Lucian and @Pi on 05.01.2016.
 */
public class QuestionsTest {

    @Override
    public String toString() {
        return "QuestionsTest{" +
                "text='" + text + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionsTest that = (QuestionsTest) o;

        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (answer1 != null ? !answer1.equals(that.answer1) : that.answer1 != null) return false;
        if (answer2 != null ? !answer2.equals(that.answer2) : that.answer2 != null) return false;
        if (answer3 != null ? !answer3.equals(that.answer3) : that.answer3 != null) return false;
        return answer4 != null ? answer4.equals(that.answer4) : that.answer4 == null;

    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (answer1 != null ? answer1.hashCode() : 0);
        result = 31 * result + (answer2 != null ? answer2.hashCode() : 0);
        result = 31 * result + (answer3 != null ? answer3.hashCode() : 0);
        result = 31 * result + (answer4 != null ? answer4.hashCode() : 0);
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

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public QuestionsTest() {
    }

    public QuestionsTest(String text, String answer1, String answer2, String answer3, String answer4) {

        this.text = text;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }

    private String text;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
}
