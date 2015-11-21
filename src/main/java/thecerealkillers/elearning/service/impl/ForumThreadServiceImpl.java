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
    public void add(ForumThread newForumThread) {

    }

    @Override
    public List<ForumThread> getThreadsOwnedByUser(String userName) {
        return null;
    }

    @Override
    public ForumThread getThreadByTitle(String threadTitle) {
        return null;
    }

    @Override
    public ForumThread getThreadByTopic(String threadTopic) {
        return null;
    }

    @Override
    public void updateThread(String oldTitle, ForumThread newThread) {

    }

    @Override
    public void deleteThreadByTitle(ForumThread threadToDelete) {

    }
}
