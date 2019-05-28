package io.javabrains.topic;

import org.springframework.data.repository.CrudRepository;

/**
 * project: course-api
 * package: io.javabrains.topic
 * file:    TopicRepository
 * created: 2019-05-28
 * author:  rotem
 */
public interface TopicRepository extends CrudRepository<Topic, String> {

}
