package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.models.Post;
import edu.miu.cs545.spring.models.PostV2;

import java.util.Collection;

public interface PostRepository {
    Post getById(Long id);
    Collection<PostV2> getByAuthor(String author);
    Post update(Post post);
    Post add(Post post);

    void delete(Long id);
    Collection<Post> getAll();
}
