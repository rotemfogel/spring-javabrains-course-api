package io.javabrains.courses;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * project: course-api
 * package: io.javabrains.course
 * file:    CourseRepository
 * created: 2019-05-28
 * author:  rotem
 */
public interface CourseRepository extends CrudRepository<Course, String> {
    List<Course> findAllByTopicId(final String id);
}
