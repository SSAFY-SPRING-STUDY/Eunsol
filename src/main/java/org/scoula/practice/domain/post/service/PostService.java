package org.scoula.practice.domain.post.service;

import org.scoula.practice.domain.post.controller.dto.PostRequest;
import org.scoula.practice.domain.post.controller.dto.PostResponse;
import org.scoula.practice.domain.post.entity.PostEntity;
import org.scoula.practice.domain.post.repository.PostRepository;
import org.scoula.practice.global.exception.CustomException;
import org.scoula.practice.global.exception.error.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse save(PostRequest request) {
        PostEntity entity = new PostEntity(request.getTitle(), request.getContent(), request.getAuthor());
        PostEntity returnedEntity = postRepository.save(entity);

        PostResponse response = new PostResponse(returnedEntity.getId(), returnedEntity.getTitle(), returnedEntity.getContent(), returnedEntity.getAuthor());
        return response;
    }

    public List<PostResponse> findAll() {
        List<PostEntity> entityList = postRepository.findAll();
        List<PostResponse> responseList = new ArrayList<>();

        for(PostEntity entity : entityList){
            PostResponse response = new PostResponse(entity.getId(), entity.getTitle(), entity.getContent(), entity.getAuthor());
            responseList.add(response);
        }
        return responseList;
    }

    public PostResponse findById(Long id) {
        PostEntity entity = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        PostResponse response = new PostResponse(entity.getId(), entity.getTitle(), entity.getContent(), entity.getAuthor());

        return response;
    }

    public void update(Long id, PostRequest request) {
        PostEntity entity = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        entity.update(request.getTitle(), request.getContent());
    }

    public void delete(Long id) {
        postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        postRepository.deleteById(id);
    }
}
