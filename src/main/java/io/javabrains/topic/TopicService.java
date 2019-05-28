package io.javabrains.topic;

import io.javabrains.common.DuplicateEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * project: course-api
 * package: io.javabrains.topic
 * file:    TopicService
 * created: 2019-05-28
 * author:  rotem
 */
@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public Topic addTopic(final Topic topic) throws DuplicateEntityException {
        if (getTopic(topic.getId()).isPresent()) throw new DuplicateEntityException(String.format("topic with id [%s] already exists", topic.getId()));
        return topicRepository.save(topic);
    }

    public Optional<Topic> getTopic(final String id) {
        return topicRepository.findById(id);
    }

    public List<Topic> getAllTopics() {
        final List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public Topic updateTopic(final Topic topic) {
        return topicRepository.save(topic);
    }

    public void deleteTopic(final String id) {
        Optional<Topic> maybeTopic = getTopic(id);
        if (maybeTopic.isPresent())
            topicRepository.delete(maybeTopic.get());
    }

    public boolean exists(final String id) {
        return topicRepository.existsById(id);
    }
}