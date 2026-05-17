package org.scoula.practice.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.scoula.practice.domain.member.controller.dto.MemberRequest;
import org.scoula.practice.domain.member.controller.dto.MemberResponse;
import org.scoula.practice.domain.member.entity.MemberEntity;
import org.scoula.practice.domain.member.repository.MemberRespository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRespository memberRespository;

    public MemberResponse save(MemberRequest request){
        MemberEntity entity = MemberRequest.toEntity(request);
        MemberEntity savedEntity = memberRespository.save(entity);

        return MemberResponse.fromEntity(savedEntity);
    }
}
