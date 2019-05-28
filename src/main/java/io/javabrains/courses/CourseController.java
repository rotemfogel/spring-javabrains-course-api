package io.javabrains.courses;

import io.javabrains.common.DuplicateEntityException;
import io.javabrains.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private CourseService courseService;

    @Autowired
    private TopicService topicService;

    private static final ResponseEntity BAD_REQUEST_RESPONSE = new ResponseEntity(BAD_REQUEST);
    private static final ResponseEntity NO_CONTENT_RESPONSE = new ResponseEntity(NO_CONTENT);
    private static final ResponseEntity OK_RESPONSE = new ResponseEntity(OK);

    @RequestMapping(method = RequestMethod.POST, value = "/topics/{topicId}/courses")
    public ResponseEntity<Course> addCourse(@PathVariable final String topicId, @RequestBody final Course course) {
        if (!topicExists(topicId)) return BAD_REQUEST_RESPONSE;
        try {
            return new ResponseEntity<>(courseService.addCourse(course), CREATED);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(CONFLICT);
        }
    }

    @RequestMapping("/topics/{topicId}/courses/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable final String topicId, @PathVariable final String id) {
        if (!topicExists(topicId)) return BAD_REQUEST_RESPONSE;
        Optional<Course> maybeCourse = courseService.getCourse(id);
        return maybeCourse.isPresent() ? new ResponseEntity<>(maybeCourse.get(), OK) : NO_CONTENT_RESPONSE;
    }

    @RequestMapping("/topic/{topicId}/courses")
    public ResponseEntity<List<Course>> getAllCourses(@PathVariable final String topicId) {
        return new ResponseEntity<>(courseService
                .getAllCourses()
                .stream()
                .filter(c -> c.getId().equals(topicId))
                .collect(Collectors.toList()), OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topic/{topicId}/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable final String topicId, @PathVariable final String id, @RequestBody final Course course) {
        if (!topicExists(topicId)) return BAD_REQUEST_RESPONSE;
        return new ResponseEntity<>(courseService.updateCourse(course), OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/topic/{topicId}/courses/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable final String topicId, @PathVariable final String id) {
        if (!topicExists(topicId)) return BAD_REQUEST_RESPONSE;
        courseService.deleteCourse(id);
        return OK_RESPONSE;
    }

    private boolean topicExists(final String id) {
        return topicService.exists(id);
    }
}
