package org.scoula.practice.domain.post.service;

import org.scoula.practice.domain.member.controller.dto.MemberResponse;
import org.scoula.practice.domain.member.entity.MemberEntity;
import org.scoula.practice.domain.member.repository.MemberRepository;
import org.scoula.practice.domain.member.service.MemberService;
import org.scoula.practice.domain.post.controller.dto.PostRequest;
import org.scoula.practice.domain.post.controller.dto.PostResponse;
import org.scoula.practice.domain.post.entity.PostEntity;
import org.scoula.practice.domain.post.repository.PostRepository;
import org.scoula.practice.global.exception.CustomException;
import org.scoula.practice.global.exception.error.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final MemberService memberService;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberService memberService, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    public PostResponse save(PostRequest request, Long authorId) {
        MemberEntity author = memberRepository.findById(authorId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        PostEntity entity = PostRequest.toEntity(request, author);
        PostEntity savedEntity = postRepository.save(entity);
        PostResponse response = PostResponse.fromEntity(savedEntity);
        return response;
    }

    public List<PostResponse> findAll() {
        List<PostEntity> entityList = postRepository.findAll();
        List<PostResponse> responseList = new ArrayList<>();

        for(PostEntity entity : entityList){
            PostResponse response = PostResponse.fromEntity(entity);
            responseList.add(response);
        }
        return responseList;
    }

    public PostResponse findById(Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        PostResponse response = PostResponse.fromEntity(post);

        return response;
    }

    @Transactional
    public void update(Long id, PostRequest request, Long authorId) {
        PostEntity entity = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if(!entity.getAuthor().getId().equals(authorId)){
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        entity.update(request.getTitle(), request.getContent());
    }

    public void delete(Long id) {
        postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        postRepository.deleteById(id);
    }
}
