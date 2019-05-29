package io.javabrains.courses;

import io.javabrains.common.exceptions.DuplicateEntityException;
import io.javabrains.common.exceptions.MissingEntityException;
import io.javabrains.topic.Topic;
import io.javabrains.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * project: course-api
 * package: io.javabrains.course
 * file:    CourseService
 * created: 2019-05-28
 * author:  rotem
 */
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final TopicService topicService;

    @Autowired
    public CourseService(TopicService topicService, CourseRepository courseRepository) {
        this.topicService = topicService;
        this.courseRepository = courseRepository;
    }

    Course addCourse(final Course course, final String topicId) throws MissingEntityException, DuplicateEntityException {
        if (getCourse(topicId, course.getId()).isPresent())
            throw new DuplicateEntityException(String.format("course with id [%s] already exists", course.getId()));
        return courseRepository.save(course);
    }

    Optional<Course> getCourse(final String topicId, final String id) throws MissingEntityException {
        if (!getTopic(topicId).isPresent())
            throw new MissingEntityException(String.format("topic [%s] does not exist", topicId));
        return courseRepository.findById(id);
    }

    List<Course> getAllCoursesByName(final String topicId, final String name) throws MissingEntityException {
        if (!getTopic(topicId).isPresent())
            throw new MissingEntityException(String.format("topic [%s] does not exist", topicId));
        return new ArrayList<>(courseRepository.findByName(name));
    }

    List<Course> getAllCoursesByTopic(final String topicId) {
        return new ArrayList<>(courseRepository.findByTopicId(topicId));
    }

    Course updateCourse(final String topicId, final Course course) throws MissingEntityException {
        if (!getTopic(topicId).isPresent())
            throw new MissingEntityException(String.format("topic [%s] does not exist", topicId));
        return courseRepository.save(course);
    }

    void deleteCourse(final String topicId, final String id) throws MissingEntityException {
        Optional<Course> maybeCourse = getCourse(topicId, id);
        maybeCourse.ifPresent(courseRepository::delete);
    }

    private Optional<Topic> getTopic(final String id) {
        return topicService.getTopic(id);
    }
}
