package org.scoula.practice.domain.post.repository;

import org.scoula.practice.domain.post.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    List<PostEntity> postList = new ArrayList<>();

    public PostEntity save(PostEntity postEntity) {
        postList.add(postEntity);
        return postEntity;
    }

    public List<PostEntity> findAll() {
        return postList;
    }

    public Optional<PostEntity> findById(Long id) {
        return postList.stream().filter(post -> post.getId().equals(id)).findAny();
    }

    public void deleteById(Long id) {
        postList.removeIf(post -> post.getId().equals(id));
    }
}
