package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.dao.ForumThreadDAO;
import thecerealkillers.elearning.model.ForumThread;

import java.util.List;

@Repository
public class ForumThreadDAOImpl implements ForumThreadDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

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
