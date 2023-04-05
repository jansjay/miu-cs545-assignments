package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
