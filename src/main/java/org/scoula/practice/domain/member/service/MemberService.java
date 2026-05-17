package org.scoula.practice.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.scoula.practice.domain.member.controller.dto.MemberRequest;
import org.scoula.practice.domain.member.controller.dto.MemberResponse;
import org.scoula.practice.domain.member.entity.MemberEntity;
import org.scoula.practice.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse save(MemberRequest request){
        MemberEntity entity = MemberRequest.toEntity(request);
        MemberEntity savedEntity = memberRepository.save(entity);

        return MemberResponse.fromEntity(savedEntity);
    }

    public MemberResponse findById(Long memberId) {
        MemberEntity entity = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        return MemberResponse.fromEntity(entity);
    }
}
