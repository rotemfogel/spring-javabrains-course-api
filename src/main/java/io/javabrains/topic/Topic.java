package io.javabrains.topic;

import lombok.*;

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
@AllArgsConstructor
@Entity
public class Topic {

    @Id
    private String id;
    private String name;
    private String description;

    public Topic(String id) {
        this(id, "", "");
    }
}
