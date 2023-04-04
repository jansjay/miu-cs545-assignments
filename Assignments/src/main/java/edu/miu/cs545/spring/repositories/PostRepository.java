package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.models.Post;

import java.util.Collection;

public interface PostRepository {
    Post getById(Long id);
    Post update(Post post);
    void delete(Long id);
    Collection<Post> getAll();
}
