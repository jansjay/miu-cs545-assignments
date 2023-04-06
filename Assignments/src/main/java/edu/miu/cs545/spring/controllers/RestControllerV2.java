package edu.miu.cs545.spring.controllers;

import edu.miu.cs545.spring.dto.PostDto;
import edu.miu.cs545.spring.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
@RequestMapping(value = "/posts", headers = "API-VERSION=2")
public class RestControllerV2 {
    @Autowired
    PostService postService;
    @Autowired
    ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<Collection<PostDto>> getByAuthor(@RequestParam("author") String author){
        return ResponseEntity.ok(postService.getByAuthor(author));
    }
}
