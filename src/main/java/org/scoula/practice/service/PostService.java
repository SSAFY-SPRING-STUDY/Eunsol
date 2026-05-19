package org.scoula.practice.service;

import org.scoula.practice.controller.dto.PostRequest;
import org.scoula.practice.controller.dto.PostResponse;
import org.scoula.practice.entity.PostEntity;
import org.scoula.practice.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        PostEntity entity = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글 없음"));
        PostResponse response = new PostResponse(entity.getId(), entity.getTitle(), entity.getContent(), entity.getAuthor());

        return response;
    }

    public void update(Long id, PostRequest request) {
        PostEntity entity = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글 없음"));

        entity.update(request.getTitle(), request.getContent());
    }

    public void delete(Long id) {
        postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글 없음"));

        postRepository.deleteById(id);
    }
}
