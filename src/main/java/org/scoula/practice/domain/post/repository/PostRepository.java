package org.scoula.practice.domain.post.repository;

import org.scoula.practice.domain.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    public PostEntity save(PostEntity postEntity);

    public List<PostEntity> findAll();

    public Optional<PostEntity> findById(Long id);

    public void deleteById(PostEntity post);
}
