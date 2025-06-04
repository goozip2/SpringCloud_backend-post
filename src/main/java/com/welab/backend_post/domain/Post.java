package com.welab.backend_post.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Table(name = "post",
        // 해당 컬럼으로 WHERE, ORDER BY, JOIN, 검색 등을 수행할 때 성능이 좋아진
        indexes = {
                @Index(columnList = "user_id"),
                @Index(columnList = "created_datetime"),
                @Index(columnList = "updated_datetime")
        })
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "title", nullable = false)
    @Getter
    private String title;

    @Column(name = "content", nullable = false)
    @Getter
    private String content;

    @Column(name = "user_id", nullable = false)
    @Getter
    @Setter
    private String userId;

    @Column(name = "created_datetime", nullable = false)
    @Getter
    private final LocalDateTime createdDatetime = LocalDateTime.now();

    @Column(name = "updated_datetime")
    @Getter
    private LocalDateTime updatedDatetime;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private final List<PostComment> comments = new ArrayList<>();

    public void setPost(String title, String contents) {
        this.title = title;
        this.content = contents;
        this.updatedDatetime = LocalDateTime.now();
    }

    public List<PostComment> getComments() {
        return this.comments;
    }

    public void addComment(PostComment comment) {
        // DB 자동 반영: JPA의 연관관계 설정 + 영속성 컨텍스트
        // 연관관계 주인(comment)에게 설정
        comment.setPost(this);
        // 리스트에 추가 (양방향 연관관계를 올바르게 관리하기 위함)
        this.comments.add(comment);
    }
}
