package com.welab.backend_post.common.exception;

public class NotFound extends ClientError {
    public NotFound(String message) {
        super("NotFound", message);
    }
}
