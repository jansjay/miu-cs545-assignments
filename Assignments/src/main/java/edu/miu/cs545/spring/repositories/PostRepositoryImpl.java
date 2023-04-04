package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.models.Post;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PostRepositoryImpl implements PostRepository{
    // A map is used instead of an ArrayList
    Map<Long, Post> posts = new HashMap<>();
    {
        posts.put(1l, new Post(1L, "Things Fall Apart", "https://en.wikipedia.org/wiki/Things_Fall_Apart","Chinua Achebe"));
        posts.put(2l, new Post(2L, "Fairy tales", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection","Hans Christian Andersen"));
        posts.put(3l, new Post(3L, "The Divine Comedy", "https://en.wikipedia.org/wiki/Divine_Comedy","Dante Alighieri"));
        posts.put(4l, new Post(4L, "The Epic Of Gilgamesh", "https://en.wikipedia.org/wiki/Epic_of_Gilgamesh","Unknown"));
        posts.put(5l, new Post(5L, "Achaemenid Empire", "https://en.wikipedia.org/wiki/Book_of_Job","Unknown"));
    }

    @Override
    public Post getById(Long id) {
        return posts.get(id);
    }

    @Override
    public Post update(Post post) {
        posts.replace(post.getId(), post);
        return posts.get(post.getId());
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
