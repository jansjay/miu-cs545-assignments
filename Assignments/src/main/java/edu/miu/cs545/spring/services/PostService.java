package edu.miu.cs545.spring.services;

import edu.miu.cs545.spring.models.Post;

import java.util.Collection;

public interface PostService {
    Post getById(Long id);
    Post update(Post post);
    void delete(Long id);
    Collection<Post> getAll();
}
