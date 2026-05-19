package org.scoula.practice.domain.member.controller.dto;

import org.scoula.practice.domain.member.entity.MemberEntity;

public record MemberResponse(Long id, String loginId, String name) {
    public static MemberResponse fromEntity(MemberEntity entity) {
        return new MemberResponse(entity.getId(), entity.getLoginId(), entity.getName());
    }
}
