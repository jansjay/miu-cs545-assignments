package edu.miu.cs545.spring.services;

import edu.miu.cs545.spring.models.Post;
import edu.miu.cs545.spring.models.PostV2;
import edu.miu.cs545.spring.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post getById(Long id) {
        return postRepository.getById(id);
    }

    @Override
    public Collection<PostV2> getByAuthor(String author) {
        return postRepository.getByAuthor(author);
    }

    @Override
    public Post add(Post post) {
        return postRepository.add(post);
    }

    @Override
    public Post update(Post post) {
        return postRepository.update(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.delete(id);
    }

    @Override
    public Collection<Post> getAll() {
        return postRepository.getAll();
    }
}
