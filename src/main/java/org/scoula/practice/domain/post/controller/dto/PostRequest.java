package org.scoula.practice.domain.post.controller.dto;

import lombok.Getter;
import org.scoula.practice.domain.member.entity.MemberEntity;
import org.scoula.practice.domain.post.entity.PostEntity;

@Getter
public class PostRequest {
    private final String title;
    private final String content;

    public PostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static PostEntity toEntity(PostRequest request, MemberEntity author) {
        return new PostEntity(request.getTitle(), request.getContent(), author);
    }
}
