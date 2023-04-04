package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.models.Post;
import edu.miu.cs545.spring.models.PostV2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository{
    @Autowired
    ModelMapper modelMapper;
    Map<Long, Post> posts = new HashMap<>();
    {
        posts.put(1L, new Post(1L, "Things Fall Apart", "https://en.wikipedia.org/wiki/Things_Fall_Apart","Chinua Achebe"));
        posts.put(2L, new Post(2L, "Fairy tales", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection","Hans Christian Andersen"));
        posts.put(3L, new Post(3L, "The Divine Comedy", "https://en.wikipedia.org/wiki/Divine_Comedy","Dante Alighieri"));
        posts.put(4L, new Post(4L, "The Epic Of Gilgamesh", "https://en.wikipedia.org/wiki/Epic_of_Gilgamesh","Unknown"));
        posts.put(5L, new Post(5L, "Achaemenid Empire", "https://en.wikipedia.org/wiki/Book_of_Job","Unknown"));
    }

    @Override
    public Post getById(Long id) {
        return posts.get(id);
    }

    @Override
    public Collection<PostV2> getByAuthor(String author) {
        return posts.values().stream()
                .filter(x-> x.getAuthor().equals(author))
                .map(x-> modelMapper.map(x, PostV2.class))
                .collect(Collectors.toList());
    }

    @Override
    public Post update(Post post) {
        posts.replace(post.getId(), post);
        return posts.get(post.getId());
    }

    @Override
    public Post add(Post post) {
        post.setId(posts.size());
        posts.put(post.getId(), post);
        return post;
    }

    @Override
    public void delete(Long id) {
        posts.remove(id);
    }

    @Override
    public Collection<Post> getAll() {
        return posts.values();
    }
}
