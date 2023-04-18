package edu.miu.cs545.spring.controllers;

import edu.miu.cs545.spring.dto.CommentDto;
import edu.miu.cs545.spring.dto.PostDto;
import edu.miu.cs545.spring.services.CommentService;
import edu.miu.cs545.spring.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
@Controller
@RequestMapping("/posts")
public class RestController {
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;
    @Autowired
    ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<Collection<PostDto>> getAll(){
        return ResponseEntity.ok(postService.getAll().stream().map(x-> modelMapper.map(x, PostDto.class)).collect(Collectors.toList()));
    }
    @GetMapping ("{id}/comments")
    ResponseEntity<Collection<CommentDto>> getComments(@PathVariable Long id){
        return ResponseEntity.ok(commentService.findByPostId(id));
    }
    @PostMapping ("{id}/comments")
    ResponseEntity<CommentDto> postComments(@PathVariable Long id, @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.addComment(id, commentDto));
    }
    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<Collection<PostDto>> getPostsWithTitle(@PathVariable("title") String title){
        return ResponseEntity.ok(postService.getPostsWithTitle(title));
    }
    @PostMapping
    public ResponseEntity<Void> addPost(@RequestBody PostDto post){
        // Post should return created with location
        // https://www.rfc-editor.org/rfc/rfc9110.html#name-post
        PostDto newPost = postService.add(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable("id") Long id){
        return ResponseEntity.ok(postService.getById(id));
    }
    @PutMapping()
    public ResponseEntity<Void> updatePost(@RequestBody PostDto post){
        // Put should return 200 with a redirect if newly created, else 200 or 204 only
        // https://www.rfc-editor.org/rfc/rfc9110.html#name-put
        Long prevId = post.getId();
        PostDto updatedPost = postService.update(post);
        if(!Objects.equals(prevId, updatedPost.getId())) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(updatedPost.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        else {
            return ResponseEntity.ok().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id){
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
