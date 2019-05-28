package io.javabrains.topic;

import io.javabrains.common.DuplicateEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * project: course-api
 * package: io.javabrains.topic
 * file:    TopicController
 * created: 2019-05-28
 * author:  rotem
 */
@RestController
public class TopicController {
    @Autowired
    private TopicService service;

    @RequestMapping(method = RequestMethod.POST, value = "/topics")
    public ResponseEntity<Topic> addTopic(@RequestBody final Topic topic) {
        try {
            Topic response = service.addTopic(topic);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping("/topics/{id}")
    public Topic getTopic(@PathVariable final String id) {
        Optional<Topic> maybeTopic = service.getTopic(id);
        return maybeTopic.isPresent() ? maybeTopic.get() : null;
    }

    @RequestMapping("/topics")
    public List<Topic> getAllTopics() {
        return service.getAllTopics();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
    public void updateTopic(@PathVariable final String id, @RequestBody final Topic topic) {
        service.updateTopic(topic);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
    public void deleteTopic(@PathVariable final String id) {
        service.deleteTopic(id);
    }
}
