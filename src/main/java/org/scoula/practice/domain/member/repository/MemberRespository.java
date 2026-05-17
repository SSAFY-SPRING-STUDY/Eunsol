package org.scoula.practice.domain.member.repository;

import org.scoula.practice.domain.member.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class MemberRespository {
    private static Map<Long, MemberEntity> memberStore = new ConcurrentHashMap<>();

    public MemberEntity save(MemberEntity entity){
        memberStore.put(entity.getId(), entity);
        MemberEntity savedEntity = memberStore.get(entity.getId());
        return savedEntity;
    }
}
