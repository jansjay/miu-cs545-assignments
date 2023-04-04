package edu.miu.cs545.spring.services;

import edu.miu.cs545.spring.models.Post;
import edu.miu.cs545.spring.models.PostV2;

import java.util.Collection;

public interface PostService {
    Post getById(Long id);
    Collection<PostV2> getByAuthor(String author);
    Post add(Post post);
    Post update(Post post);
    void delete(Long id);
    Collection<Post> getAll();
}
