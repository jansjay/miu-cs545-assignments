package edu.miu.cs545.spring.services;

import edu.miu.cs545.spring.models.Post;
import edu.miu.cs545.spring.models.User;

import java.util.Collection;

public interface UserService {
    User getById(Long id);
    User add(User user);
    User update(User user);
    void delete(Long id);
    Collection<User> getAll();
    Collection<Post> getUserPostsAll(Long id);
}
