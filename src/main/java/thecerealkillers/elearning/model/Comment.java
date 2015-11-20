package thecerealkillers.elearning.model;

import java.util.Date;

public class Comment {

    private String owner;
    private Date timeStamp;
    private String meessage;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMeessage() {
        return meessage;
    }

    public void setMeessage(String meessage) {
        this.meessage = meessage;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "owner='" + owner + '\'' +
                ", timeStamp=" + timeStamp +
                ", meessage='" + meessage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (!owner.equals(comment.owner)) return false;
        if (!timeStamp.equals(comment.timeStamp)) return false;
        return meessage.equals(comment.meessage);

    }

    @Override
    public int hashCode() {
        int result = owner.hashCode();
        result = 31 * result + timeStamp.hashCode();
        result = 31 * result + meessage.hashCode();
        return result;
    }
}
