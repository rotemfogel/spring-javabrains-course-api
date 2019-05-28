package io.javabrains.common;

import org.springframework.http.ResponseEntity;

/**
 * project: course-api
 * package: io.javabrains.common
 * file:    DefaultController
 * created: 2019-05-28
 * author:  rotem
 */
public interface DefaultController {
    static ResponseEntity badRequest(final EntityType entityType, final String id) {
        return ResponseEntity.badRequest().body(new Response(400, String.format("%s [%s] does not exist", entityType.name(), id)));
    }
}
