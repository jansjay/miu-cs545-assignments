package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.dto.PostDto;

import java.util.Collection;

public interface PostV2Repository {
    Collection<PostDto> getByAuthor(String author);
}
