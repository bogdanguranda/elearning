package thecerealkillers.elearning.model;

/**
 * Created by Dani.
 */
public class CommentUpdateInfo {
    private Integer commentID;
    private String newMessage;

    public CommentUpdateInfo() {
    }

    public CommentUpdateInfo(Integer commentID, String newMessage) {
        this.commentID = commentID;
        this.newMessage = newMessage;
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentUpdateInfo that = (CommentUpdateInfo) o;

        if (commentID != null ? !commentID.equals(that.commentID) : that.commentID != null) return false;
        return newMessage != null ? newMessage.equals(that.newMessage) : that.newMessage == null;

    }

    @Override
    public int hashCode() {
        int result = commentID != null ? commentID.hashCode() : 0;
        result = 31 * result + (newMessage != null ? newMessage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommentUpdateInfo{" +
                "commentID=" + commentID +
                ", newMessage='" + newMessage + '\'' +
                '}';
    }
}
