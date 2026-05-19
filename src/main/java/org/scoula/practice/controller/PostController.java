package org.scoula.practice.controller;

import org.scoula.practice.controller.dto.PostRequest;
import org.scoula.practice.controller.dto.PostResponse;
import org.scoula.practice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@RequestBody PostRequest request) {
        PostResponse response = postService.save(request);
        return response;
    }

    @GetMapping("/api/posts")
    public List<PostResponse> findAllPosts(){
        return postService.findAll();
    }

    @GetMapping("/api/posts/{id}")
    public PostResponse findPostById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PutMapping("/api/posts/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
        postService.update(id, request);
    }

    @DeleteMapping("/api/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }
}
