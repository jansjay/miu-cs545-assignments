package edu.miu.cs545.spring.services;

import edu.miu.cs545.spring.dto.PostDto;

import java.util.Collection;

public interface PostService {
    PostDto getById(Long id);
    Collection<PostDto> getByAuthor(String author);
    PostDto add(PostDto post);
    PostDto update(PostDto post);
    void delete(Long id);
    Collection<PostDto> getAll();
    Collection<PostDto> findPostsByUserId(Long userId);
    Collection<PostDto> findPostsByUserIdPostId(Long userId, Long postId);
    Collection<PostDto> getPostsWithTitle(String title);
}
