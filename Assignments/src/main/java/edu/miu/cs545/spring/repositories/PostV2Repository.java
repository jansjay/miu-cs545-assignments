package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.models.PostV2;

import java.util.Collection;

public interface PostV2Repository {
    Collection<PostV2> getByAuthor(String author);
}
