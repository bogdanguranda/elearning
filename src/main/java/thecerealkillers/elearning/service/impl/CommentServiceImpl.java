package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.CommentDAO;
import thecerealkillers.elearning.model.Comment;
import thecerealkillers.elearning.service.CommentService;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public void addComment(String owner, String message, String threadTitle) {
        commentDAO.addComment(owner, message, threadTitle);
    }

    @Override
    public Comment getCommentByOwnerAndTimeStamp(String owner, Date timeStamp) {
        return commentDAO.getCommentByOwnerAndTimeStamp(owner, timeStamp);
    }

    @Override
    public List<Comment> getCommentsForThread(String threadTitle) {
        return commentDAO.getCommentsForThread(threadTitle);
    }

    @Override
    public void updateComment(String owner, Date timeStamp, String newMessage) {
        commentDAO.updateComment(owner, timeStamp, newMessage);
    }

    @Override
    public void deleteComment(String owner, Date timeStamp) {
        commentDAO.deleteComment(owner, timeStamp);
    }
}
