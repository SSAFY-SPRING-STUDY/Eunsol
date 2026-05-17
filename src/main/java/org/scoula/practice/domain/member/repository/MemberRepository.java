package org.scoula.practice.domain.member.repository;

import org.scoula.practice.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    public MemberEntity save(MemberEntity entity);

    public Optional<MemberEntity> findByLoginId(String loginId);

    public Optional<MemberEntity> findById(Long memberId);
}
