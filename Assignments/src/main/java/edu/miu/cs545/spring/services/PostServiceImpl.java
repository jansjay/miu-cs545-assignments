package edu.miu.cs545.spring.services;

import edu.miu.cs545.spring.models.Post;
import edu.miu.cs545.spring.models.PostV2;
import edu.miu.cs545.spring.repositories.PostRepository;
import edu.miu.cs545.spring.repositories.PostV2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostV2Repository postV2Repository;
    @Override
    public Post getById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<PostV2> getByAuthor(String author) {
        return postV2Repository.getByAuthor(author);
    }

    @Override
    public Post add(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Collection<Post> getAll() {
        Collection<Post> posts = new ArrayList<>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }
}
