package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.ForumThreadDAO;
import thecerealkillers.elearning.model.ForumThread;
import thecerealkillers.elearning.service.ForumThreadService;

import java.util.List;

@Service
public class ForumThreadServiceImpl implements ForumThreadService {

    @Autowired
    private ForumThreadDAO forumThreadDAO;

    @Override
    public void add(ForumThread newForumThread, String topicTitle) {
        forumThreadDAO.add(newForumThread, topicTitle);
    }

    @Override
    public List<ForumThread> getAll() {
        return forumThreadDAO.getAll();
    }

    @Override
    public List<ForumThread> getThreadsOwnedByUser(String userName) {
        return forumThreadDAO.getThreadsOwnedByUser(userName);
    }

    @Override
    public ForumThread getThreadByTitle(String threadTitle) {
        return forumThreadDAO.getThreadByTitle(threadTitle);
    }

    @Override
    public List<ForumThread> getThreadsForTopic(String topic) {
        return forumThreadDAO.getThreadsForTopic(topic);
    }

    @Override
    public void updateThread(String oldTitle, ForumThread newThread) {
        forumThreadDAO.updateThread(oldTitle, newThread);
    }

    @Override
    public void deleteThreadByTitle(String threadToDelete) {
        forumThreadDAO.deleteThreadByTitle(threadToDelete);
    }
}
