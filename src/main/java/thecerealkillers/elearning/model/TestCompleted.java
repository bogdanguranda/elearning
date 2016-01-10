package thecerealkillers.elearning.model;

import java.util.List;

/**
 * Created with love by Lucian and @Pi on 05.01.2016.
 */
@SuppressWarnings("unused")
public class TestCompleted {

    private int userPoints;
    private int totalPoints;
    private int attemptsRemaining;
    private List<Answer> wrongAnswers;

    @Override
    public String toString() {
        return "TestCompleted{" +
                "userPoints=" + userPoints +
                ", totalPoints=" + totalPoints +
                ", attemptsRemaining=" + attemptsRemaining +
                ", wrongAnswers=" + wrongAnswers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCompleted that = (TestCompleted) o;

        return userPoints == that.userPoints && totalPoints == that.totalPoints && attemptsRemaining == that.attemptsRemaining && (wrongAnswers != null ? wrongAnswers.equals(that.wrongAnswers) : that.wrongAnswers == null);

    }

    @Override
    public int hashCode() {
        int result = userPoints;
        result = 31 * result + totalPoints;
        result = 31 * result + attemptsRemaining;
        result = 31 * result + (wrongAnswers != null ? wrongAnswers.hashCode() : 0);
        return result;
    }

    public int getUserPoints() {

        return userPoints;
    }

    public void setUserPoints(int userPoints) {
        this.userPoints = userPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getAttemptsRemaining() {
        return attemptsRemaining;
    }

    public void setAttemptsRemaining(int attemptsRemaining) {
        this.attemptsRemaining = attemptsRemaining;
    }

    public List<Answer> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(List<Answer> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public TestCompleted() {

    }

    public TestCompleted(int userPoints, int totalPoints, int attemptsRemaining, List<Answer> wrongAnswers) {

        this.userPoints = userPoints;
        this.totalPoints = totalPoints;
        this.attemptsRemaining = attemptsRemaining;
        this.wrongAnswers = wrongAnswers;
    }
}
