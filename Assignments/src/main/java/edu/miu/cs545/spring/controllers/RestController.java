package edu.miu.cs545.spring.controllers;

import edu.miu.cs545.spring.dto.PostDto;
import edu.miu.cs545.spring.models.Post;
import edu.miu.cs545.spring.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/posts")
public class RestController {
    @Autowired
    PostService postService;
    @Autowired
    ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<Collection<PostDto>> getAll(){
        return ResponseEntity.ok(postService.getAll().stream().map(x-> modelMapper.map(x, PostDto.class)).collect(Collectors.toList()));
    }
    @PostMapping
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto post){
        return ResponseEntity.ok(modelMapper.map(postService.add(modelMapper.map(post, Post.class)), PostDto.class));
    }
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable("id") Long id){
        return ResponseEntity.ok(modelMapper.map(postService.getById(id), PostDto.class));
    }
    @PutMapping()
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto post){
        return ResponseEntity.ok(modelMapper.map(postService.update(modelMapper.map(post, Post.class)), PostDto.class));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id){
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
