package thecerealkillers.elearning.model;

/**
 * Created with love by Lucian and @Pi on 05.01.2016.
 */
@SuppressWarnings("unused")
public class Answer {
    private String text;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    @Override
    public String toString() {
        return "Answer{" +
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

        Answer answer = (Answer) o;

        return text != null ? text.equals(answer.text) : answer.text == null && (answer1 != null ? answer1.equals(answer.answer1) : answer.answer1 == null && (answer2 != null ? answer2.equals(answer.answer2) : answer.answer2 == null && (answer3 != null ? answer3.equals(answer.answer3) : answer.answer3 == null && (answer4 != null ? answer4.equals(answer.answer4) : answer.answer4 == null))));

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

    public Answer() {

    }

    public Answer(String text, String answer1, String answer2, String answer3, String answer4) {

        this.text = text;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }
}
