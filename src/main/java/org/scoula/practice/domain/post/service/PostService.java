package org.scoula.practice.domain.post.service;

import org.scoula.practice.domain.member.controller.dto.MemberResponse;
import org.scoula.practice.domain.member.service.MemberService;
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

    private final MemberService memberService;
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository, MemberService memberService) {
        this.postRepository = postRepository;
        this.memberService = memberService;
    }

    public PostResponse save(PostRequest request, Long authorId) {
        PostEntity entity = PostRequest.toEntity(request, authorId);
        PostEntity savedEntity = postRepository.save(entity);
        MemberResponse memberResponse = memberService.findById(authorId);

        PostResponse response = PostResponse.fromEntity(savedEntity, memberResponse);
        return response;
    }

    public List<PostResponse> findAll() {
        List<PostEntity> entityList = postRepository.findAll();
        List<PostResponse> responseList = new ArrayList<>();

        for(PostEntity entity : entityList){
            MemberResponse memberResponse = memberService.findById(entity.getAuthorId());
            PostResponse response = PostResponse.fromEntity(entity, memberResponse);
            responseList.add(response);
        }
        return responseList;
    }

    public PostResponse findById(Long id) {
        PostEntity entity = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        MemberResponse memberResponse = memberService.findById(entity.getAuthorId());
        PostResponse response = PostResponse.fromEntity(entity, memberResponse);

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
