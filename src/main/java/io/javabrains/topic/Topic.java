package io.javabrains.topic;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "topics")
public class Topic {

    @Id
    private String id;
    private String name;
    private String description;

}
