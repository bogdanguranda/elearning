package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.TopicDAO;
import thecerealkillers.elearning.model.Topic;
import thecerealkillers.elearning.service.TopicService;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDAO topicDAO;

    @Override
    public void add(Topic newTopic) {
        topicDAO.add(newTopic);
    }

    @Override
    public Topic get(String title) {
        return topicDAO.get(title);
    }

    @Override
    public List<Topic> getAll() {
        return topicDAO.getAll();
    }

    @Override
    public void update(String title, Topic newTopicData) {
        topicDAO.update(title, newTopicData);
    }

    @Override
    public void delete(String title) {
        topicDAO.delete(title);
    }
}
