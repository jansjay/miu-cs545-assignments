package edu.miu.cs545.spring.services;

import edu.miu.cs545.spring.dto.PostDto;
import edu.miu.cs545.spring.dto.UserDto;

import java.util.Collection;

public interface UserService {
    UserDto getById(Long id);
    UserDto add(UserDto user);
    UserDto update(UserDto user);
    void delete(Long id);
    Collection<UserDto> getAll();
    Collection<UserDto> getUsersWithNoOfPostsGreaterThan(Long count);
    Collection<PostDto> getUserPostsAll(Long id);
}
