package io.javabrains.common.exceptions;

/**
 * project: course-api
 * package: io.javabrains.topic
 * file:    MissingEntityException
 * created: 2019-05-28
 * author:  rotem
 */
public class MissingEntityException extends Exception {
    public MissingEntityException(String s) {
        super(s);
    }
}
