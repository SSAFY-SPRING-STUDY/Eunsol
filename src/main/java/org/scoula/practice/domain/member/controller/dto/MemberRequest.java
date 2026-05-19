package org.scoula.practice.domain.member.controller.dto;

import org.scoula.practice.domain.member.entity.MemberEntity;

public record MemberRequest(
        String loginId, 
        String password, 
        String name
        
        //MemberRequest request가 있을 때
        //request.getLoginId() X
        //request.loginId() O -> getter 내장되어 있음
) {
    public static MemberEntity toEntity(MemberRequest request) {
        return new MemberEntity(request.loginId(), request.password(), request.name());
    }
}
