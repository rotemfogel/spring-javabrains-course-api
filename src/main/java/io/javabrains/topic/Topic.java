package io.javabrains.topic;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * project: course-api
 * package: io.javabrains.topic
 * file:    Topic
 * created: 2019-05-28
 * author:  rotem
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Topic {

    @Id
    private String id;
    private String name;
    private String description;
}
