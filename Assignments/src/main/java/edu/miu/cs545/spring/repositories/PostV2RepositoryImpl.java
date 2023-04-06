package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.dto.PostDto;
import edu.miu.cs545.spring.models.PostV2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PostV2RepositoryImpl implements PostV2Repository{
    @Autowired
    ModelMapper modelMapper;
    Map<Long, PostV2> posts = new HashMap<>();
    {
        posts.put(1L, new PostV2(1L, "Things Fall Apart", "https://en.wikipedia.org/wiki/Things_Fall_Apart","Chinua Achebe"));
        posts.put(2L, new PostV2(2L, "Fairy tales", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection","Hans Christian Andersen"));
        posts.put(3L, new PostV2(3L, "The Divine Comedy", "https://en.wikipedia.org/wiki/Divine_Comedy","Dante Alighieri"));
        posts.put(4L, new PostV2(4L, "The Epic Of Gilgamesh", "https://en.wikipedia.org/wiki/Epic_of_Gilgamesh","Unknown"));
        posts.put(5L, new PostV2(5L, "Achaemenid Empire", "https://en.wikipedia.org/wiki/Book_of_Job","Unknown"));
    }

    @Override
    public Collection<PostDto> getByAuthor(String author) {
        return posts.values().stream()
                .filter(x-> x.getAuthor().equals(author))
                .map(x-> modelMapper.map(x, PostV2.class))
                .toList().stream().map(x-> modelMapper.map(x, PostDto.class)).toList();
    }
}
