package io.javabrains.courses;

import io.javabrains.common.DefaultController;
import io.javabrains.common.DuplicateEntityException;
import io.javabrains.common.EntityType;
import io.javabrains.topic.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

/**
 * project: course-api
 * package: io.javabrains.course
 * file:    CourseController
 * created: 2019-05-28
 * author:  rotem
 */
@RestController
public class CourseController {
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    private final TopicService topicService;
    private final CourseService courseService;

    @Autowired
    public CourseController(TopicService topicService, CourseService courseService) {
        this.topicService = topicService;
        this.courseService = courseService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics/{topicId}/courses")
    public ResponseEntity addCourse(@PathVariable final String topicId, @RequestBody final Course course) {
        if (!topicExists(topicId)) return DefaultController.badRequest(EntityType.Topic, topicId);
        try {
            return new ResponseEntity<>(courseService.addCourse(course.setTopic(topicId)), CREATED);
        } catch (DuplicateEntityException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(CONFLICT);
        }
    }

    @RequestMapping("/topics/{topicId}/courses/{id}")
    public ResponseEntity getCourse(@PathVariable final String topicId, @PathVariable final String id) {
        if (!topicExists(topicId)) return DefaultController.badRequest(EntityType.Topic, topicId);
        return ResponseEntity.of(courseService.getCourse(id));
    }

    @RequestMapping("/topics/{topicId}/courses")
    public ResponseEntity getAllCourses(@PathVariable final String topicId) {
        if (!topicExists(topicId)) return DefaultController.badRequest(EntityType.Topic, topicId);
        return ResponseEntity.of(Optional.of(courseService.getAllCoursesByTopic(topicId)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{topicId}/courses/{id}")
    public ResponseEntity updateCourse(@PathVariable final String topicId, @PathVariable final String id, @RequestBody final Course course) {
        if (!topicExists(topicId)) return DefaultController.badRequest(EntityType.Topic, topicId);
        if (!course.getId().equals(id)) return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(courseService.updateCourse(course.setTopic(topicId)), OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{topicId}/courses/{id}")
    public ResponseEntity deleteCourse(@PathVariable final String topicId, @PathVariable final String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean topicExists(final String id) {
        return topicService.exists(id);
    }
}
