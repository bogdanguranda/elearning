package thecerealkillers.elearning.model;

/**
 * Created with love by Lucian and @Pi on 02.01.2016.
 */
public class UserPoints {

    private String attemptNumber;
    private String points;

    @Override
    public String toString() {
        return "UserPoints{" +
                "attemptNumber='" + attemptNumber + '\'' +
                ", points='" + points + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPoints that = (UserPoints) o;

        return attemptNumber != null ? attemptNumber.equals(that.attemptNumber) : that.attemptNumber == null && (points != null ? points.equals(that.points) : that.points == null);

    }

    @Override
    public int hashCode() {
        int result = attemptNumber != null ? attemptNumber.hashCode() : 0;
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }

    public String getAttemptNumber() {

        return attemptNumber;
    }

    public void setAttemptNumber(String attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public UserPoints(String attemptNumber, String points) {

        this.attemptNumber = attemptNumber;
        this.points = points;
    }
}
