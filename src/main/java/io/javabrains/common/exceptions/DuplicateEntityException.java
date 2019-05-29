package io.javabrains.common.exceptions;

/**
 * project: course-api
 * package: io.javabrains.topic
 * file:    DuplicateTopicException
 * created: 2019-05-28
 * author:  rotem
 */
public class DuplicateEntityException extends Exception {
    public DuplicateEntityException(String s) {
        super(s);
    }
}
