package org.scoula.practice.domain.post.controller.dto;

import org.scoula.practice.domain.member.controller.dto.MemberResponse;
import org.scoula.practice.domain.post.entity.PostEntity;

public record PostResponse(Long id, String title, String content, MemberResponse memberInfo) {

    public static PostResponse fromEntity(PostEntity entity, MemberResponse memberResponse) {
        return new PostResponse(entity.getId(), entity.getTitle(), entity.getContent(), memberResponse);
    }
}
