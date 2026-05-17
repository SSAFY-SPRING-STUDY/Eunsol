package org.scoula.practice.domain.member.entity;

import lombok.Getter;

@Getter
public class MemberEntity {
    private static Long AUTO_INCREMENT = 1L;

    private long id;
    private String loginId;
    private String password;
    private String name;

    public MemberEntity(String loginId, String password, String name) {
        this.id = AUTO_INCREMENT++;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }
}
