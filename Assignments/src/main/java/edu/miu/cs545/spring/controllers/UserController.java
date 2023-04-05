package edu.miu.cs545.spring.controllers;

import edu.miu.cs545.spring.dto.PostDto;
import edu.miu.cs545.spring.dto.UserDto;
import edu.miu.cs545.spring.models.User;
import edu.miu.cs545.spring.services.UserService;
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
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<Collection<UserDto>> getAll(){
        return ResponseEntity.ok(userService.getAll().stream().map(x-> modelMapper.map(x, UserDto.class)).collect(Collectors.toList()));
    }
    @GetMapping("/{id}/posts")
    public ResponseEntity<Collection<PostDto>> getUserPostsAll(@PathVariable("id") Long userid){
        return ResponseEntity.ok(userService.getUserPostsAll(userid).stream().map(x-> modelMapper.map(x, PostDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/noOfPostsGreaterThan/{count}")
    public ResponseEntity<Collection<UserDto>> getUsersWithNoOfPostsGreaterThan(@PathVariable("count") Long count){
        return ResponseEntity.ok(userService.getUsersWithNoOfPostsGreaterThan(count).stream().map(x-> modelMapper.map(x, UserDto.class)).collect(Collectors.toList()));
    }
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user){
        // Post should return created with location
        // https://www.rfc-editor.org/rfc/rfc9110.html#name-post
        User newUser = userService.add(modelMapper.map(user, User.class));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(modelMapper.map(userService.getById(id), UserDto.class));
    }
    @PutMapping()
    public ResponseEntity<Void> updateUser(@RequestBody UserDto user){
        // Put should return 200 with a redirect if newly created, else 200 or 204 only
        // https://www.rfc-editor.org/rfc/rfc9110.html#name-put
        Long prevId = user.getId();
        User updatedUser = userService.update(modelMapper.map(user, User.class));
        if(!Objects.equals(prevId, updatedUser.getId())) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(updatedUser.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        else {
            return ResponseEntity.ok().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
