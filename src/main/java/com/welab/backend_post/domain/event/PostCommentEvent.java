package com.welab.backend_post.domain.event;

import com.welab.backend_post.domain.PostComment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostCommentEvent {
    public static final String Topic = "postcomment";
    private String action;
    private String userId;
    private String title;
    private String message;
    private LocalDateTime eventTime;

    public static PostCommentEvent fromEntity(String action, PostComment postComment) {
        PostCommentEvent message = new PostCommentEvent();
        message.action = action;
        message.userId = postComment.getUserId();
        message.title = "댓글 알림";
        message.message = "게시글에 댓글이 달렸습니다.";
        message.eventTime = LocalDateTime.now();
        return message;
    }
}
