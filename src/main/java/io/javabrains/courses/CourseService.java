package io.javabrains.courses;

import io.javabrains.common.DuplicateEntityException;
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
    @Autowired
    private CourseRepository courseRepository;

    public Course addCourse(final Course course) throws DuplicateEntityException {
        if (getCourse(course.getId()).isPresent()) throw new DuplicateEntityException(String.format("course with id [%s] already exists", course.getId()));
        return courseRepository.save(course);
    }

    public Optional<Course> getCourse(final String id) {
        return courseRepository.findById(id);
    }

    public List<Course> getAllCourses() {
        final List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    public Course updateCourse(final Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(final String id) {
        Optional<Course> maybeCourse = getCourse(id);
        if (maybeCourse.isPresent())
            courseRepository.delete(maybeCourse.get());
    }
}
