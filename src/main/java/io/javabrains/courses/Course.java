package io.javabrains.courses;

import io.javabrains.topic.Topic;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * project: course-api
 * package: io.javabrains.course
 * file:    Course
 * created: 2019-05-28
 * author:  rotem
 */
@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
class Course {

    @Id
    private String id;
    private String name;
    private String description;

    @ManyToOne
    private Topic topic;

    Course setTopic(final String topicId) {
        this.topic = new Topic(topicId);
        return this;
    }
}
