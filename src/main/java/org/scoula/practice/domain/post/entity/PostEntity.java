package org.scoula.practice.domain.post.entity;


import lombok.Getter;

@Getter
public class PostEntity {
    private static long AUTO_INCREMENT = 1L;

    private Long id;
    private String title;
    private String content;
    private Long authorId;

    public PostEntity(String title, String content, Long authorId) {
        this.id = AUTO_INCREMENT++;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
