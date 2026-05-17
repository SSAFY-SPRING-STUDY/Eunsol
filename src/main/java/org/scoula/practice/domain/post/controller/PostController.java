package org.scoula.practice.domain.post.controller;

import org.scoula.practice.domain.ApiResponse;
import org.scoula.practice.domain.auth.service.AuthService;
import org.scoula.practice.domain.auth.util.AuthorizationUtils;
import org.scoula.practice.domain.post.controller.dto.PostRequest;
import org.scoula.practice.domain.post.controller.dto.PostResponse;
import org.scoula.practice.domain.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;
    private AuthService authService;

    @Autowired
    public PostController(PostService postService, AuthService authService) {
        this.postService = postService;
        this.authService = authService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PostResponse> createPost(@RequestBody PostRequest request, @RequestHeader("Authorization") String authHeader) {
        String token = AuthorizationUtils.getAccessToken(authHeader);
        Long memberId = authService.getMemberId(token);
        PostResponse response = postService.save(request, memberId);
        return ApiResponse.success(response);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<PostResponse>> findAllPosts(){
        return ApiResponse.success(postService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<PostResponse> findPostById(@PathVariable Long id) {
        PostResponse response = postService.findById(id);
        return ApiResponse.success(response);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> updatePost(@PathVariable Long id, @RequestBody PostRequest request, @RequestHeader("Authorization") String authHeader) {
        String token = AuthorizationUtils.getAccessToken(authHeader);
        Long authorId = authService.getMemberId(token);
        postService.update(id, request, authorId);
        return ApiResponse.success();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ApiResponse.success();
    }
}
