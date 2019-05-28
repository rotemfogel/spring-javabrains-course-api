package io.javabrains.courses;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

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
@NoArgsConstructor
@Entity
public class Course {

    @Id
    private String id;
    private String name;
    private String description;
}
