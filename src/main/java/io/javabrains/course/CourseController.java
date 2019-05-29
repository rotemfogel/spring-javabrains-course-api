package io.javabrains.course;

import io.javabrains.common.exceptions.DuplicateEntityException;
import io.javabrains.common.exceptions.MissingEntityException;
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

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics/{topicId}/courses")
    public ResponseEntity addCourse(@PathVariable final String topicId, @RequestBody final Course course) {
        try {
            return new ResponseEntity<>(courseService.addCourse(course, topicId), CREATED);
        } catch (DuplicateEntityException | MissingEntityException e) {
            log.error("error adding course", e);
            return new ResponseEntity(e instanceof MissingEntityException ? BAD_REQUEST : CONFLICT);
        }
    }

    @RequestMapping("/topics/{topicId}/courses/{id}")
    public ResponseEntity getCourse(@PathVariable final String topicId, @PathVariable final String id) {
        try {
            return ResponseEntity.of(courseService.getCourse(topicId, id));
        } catch (MissingEntityException e) {
            log.error("error getting course", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping("/topics/{topicId}/courses")
    public ResponseEntity getAllCourses(@PathVariable final String topicId) {
        return ResponseEntity.of(Optional.of(courseService.getAllCoursesByTopic(topicId)));
    }

    @RequestMapping("/topics/{topicId}/courses/{name}")
    public ResponseEntity getByName(@PathVariable String topicId, @PathVariable String name) {
        return ResponseEntity.of(Optional.of(courseService.getAllCoursesByName(name)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{topicId}/courses/{id}")
    public ResponseEntity updateCourse(@PathVariable final String topicId, @PathVariable final String id, @RequestBody final Course course) {
        if (!course.getId().equals(id)) return ResponseEntity.badRequest().build();
        try {
            return new ResponseEntity<>(courseService.updateCourse(topicId, course), OK);
        } catch (MissingEntityException e) {
            log.error("error updating course", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{topicId}/courses/{id}")
    public ResponseEntity deleteCourse(@PathVariable final String topicId, @PathVariable final String id) {
        try {
            courseService.deleteCourse(topicId, id);
            return ResponseEntity.ok().build();
        } catch (MissingEntityException e) {
            log.error("error deleting course", e);
            return ResponseEntity.badRequest().build();
        }
    }


}
