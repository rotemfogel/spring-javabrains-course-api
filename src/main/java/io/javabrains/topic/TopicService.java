package io.javabrains.topic;

import io.javabrains.common.exceptions.DuplicateEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    Topic addTopic(final Topic topic) throws DuplicateEntityException {
        if (getTopic(topic.getId()).isPresent())
            throw new DuplicateEntityException(String.format("topic with id [%s] already exists", topic.getId()));
        return topicRepository.save(topic);
    }

    @Cacheable(value = "topics")
    public Optional<Topic> getTopic(final String id) {
        return topicRepository.findById(id);
    }

    @Cacheable(value = "topics")
    public List<Topic> getAllTopics() {
        final List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    Topic updateTopic(final Topic topic) {
        return topicRepository.save(topic);
    }

    void deleteTopic(final String id) {
        Optional<Topic> maybeTopic = getTopic(id);
        maybeTopic.ifPresent(topicRepository::delete);
    }
}
