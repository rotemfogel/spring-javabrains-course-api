package io.javabrains.courses;

import io.javabrains.topic.Topic;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
@Table(name = "courses")
@NoArgsConstructor
class Course {

    @Id
    private String id;
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Topic topic;
}
