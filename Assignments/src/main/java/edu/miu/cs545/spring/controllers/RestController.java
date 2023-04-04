package edu.miu.cs545.spring.controllers;

import edu.miu.cs545.spring.models.Post;
import edu.miu.cs545.spring.services.PostService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/posts")
public class RestController {
    @Autowired
    PostService postService;
    @GetMapping
    public ResponseEntity<Collection<Post>> getAll(){
        return ResponseEntity.ok(postService.getAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") Long id){
        return ResponseEntity.ok(postService.getById(id));
    }
    @PutMapping()
    public ResponseEntity<Post> updatePost(@RequestBody Post post){
        return ResponseEntity.ok(postService.update(post));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id){
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
