package io.javabrains.topic;

import io.javabrains.common.DuplicateEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * project: course-api
 * package: io.javabrains.topic
 * file:    TopicController
 * created: 2019-05-28
 * author:  rotem
 */
@RestController
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics")
    public ResponseEntity addTopic(@RequestBody final Topic topic) {
        try {
            return new ResponseEntity<>(topicService.addTopic(topic), CREATED);
        } catch (DuplicateEntityException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @RequestMapping("/topics/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable final String id) {
        Optional<Topic> maybeTopic = topicService.getTopic(id);
        return ResponseEntity.of(maybeTopic);
    }

    @RequestMapping("/topics")
    public ResponseEntity<List<Topic>> getAllTopics() {
        return ResponseEntity.of(Optional.of(topicService.getAllTopics()));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
    public ResponseEntity updateTopic(@PathVariable final String id, @RequestBody final Topic topic) {
        if (!topic.getId().equals(id)) return ResponseEntity.badRequest().build();
        return ResponseEntity.of(Optional.of(topicService.updateTopic(topic)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
    public ResponseEntity deleteTopic(@PathVariable final String id) {
        topicService.deleteTopic(id);
        return ResponseEntity.ok().build();
    }
}
