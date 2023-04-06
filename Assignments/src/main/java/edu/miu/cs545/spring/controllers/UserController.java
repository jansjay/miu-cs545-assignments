package edu.miu.cs545.spring.controllers;

import edu.miu.cs545.spring.dto.PostDto;
import edu.miu.cs545.spring.dto.UserDto;
import edu.miu.cs545.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<Collection<UserDto>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping("/{id}/posts")
    public ResponseEntity<Collection<PostDto>> getUserPostsAll(@PathVariable("id") Long userid){
        return ResponseEntity.ok(userService.getUserPostsAll(userid));
    }

    @GetMapping("/noOfPostsGreaterThan/{count}")
    public ResponseEntity<Collection<UserDto>> getUsersWithNoOfPostsGreaterThan(@PathVariable("count") Long count){
        return ResponseEntity.ok(userService.getUsersWithNoOfPostsGreaterThan(count));
    }
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user){
        // Post should return created with location
        // https://www.rfc-editor.org/rfc/rfc9110.html#name-post
        UserDto newUser = userService.add(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getById(id));
    }
    @PutMapping()
    public ResponseEntity<Void> updateUser(@RequestBody UserDto user){
        // Put should return 200 with a redirect if newly created, else 200 or 204 only
        // https://www.rfc-editor.org/rfc/rfc9110.html#name-put
        Long prevId = user.getId();
        UserDto updatedUser = userService.update(user);
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
