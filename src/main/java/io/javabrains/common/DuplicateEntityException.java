package io.javabrains.common;

/**
 * project: course-api
 * package: io.javabrains.topic
 * file:    DuplicateTopicException
 * created: 2019-05-28
 * author:  rotem
 */
public class DuplicateEntityException extends Exception {
    public DuplicateEntityException() {
        super();
    }

    public DuplicateEntityException(String s) {
        super(s);
    }

    public DuplicateEntityException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
