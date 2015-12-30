package thecerealkillers.elearning.model;

/**
 * Created with love by Lucian and @Pi on 29.12.2015.
 */
public class Answer {

    private int questionID;
    private String answerText;
    private boolean correct;

    @Override
    public String toString() {
        return "Answer{" +
                "questionID=" + questionID +
                ", answerText='" + answerText + '\'' +
                ", correct=" + correct +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (questionID != answer.questionID) return false;
        if (correct != answer.correct) return false;
        return answerText != null ? answerText.equals(answer.answerText) : answer.answerText == null;

    }

    @Override
    public int hashCode() {
        int result = questionID;
        result = 31 * result + (answerText != null ? answerText.hashCode() : 0);
        result = 31 * result + (correct ? 1 : 0);
        return result;
    }

    public int getQuestionID() {

        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Answer(int questionID, String answerText, boolean correct) {
        this.questionID = questionID;
        this.answerText = answerText;
        this.correct = correct;

    }
}
