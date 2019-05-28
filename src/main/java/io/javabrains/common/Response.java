package io.javabrains.common;

import lombok.*;

/**
 * project: course-api
 * package: io.javabrains.common
 * file:    Response
 * created: 2019-05-28
 * author:  rotem
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Response {
    private Integer code;
    private String message;
}
