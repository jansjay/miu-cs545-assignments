package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface PostRepository extends CrudRepository<Post, Long> {
    @Query("SELECT user.posts FROM User user where user.id = :id")
    Collection<Post> findPostsByUserId(@Param("id") Long id);
    @Query(value = "SELECT user_id FROM post WHERE id = :id", nativeQuery = true)
    Long getUserId(@Param("id") Long id);
}
