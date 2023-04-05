package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.models.Post;
import edu.miu.cs545.spring.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT user.posts FROM User user where user.id = :id")
    Collection<Post> getPosts(@Param("id") Long id);
    @Query(value = "SELECT * FROM users u WHERE u.id IN (SELECT p.user_id FROM post p GROUP BY p.user_id HAVING count(*) > :count)", nativeQuery = true)
    Collection<User> getUsersWithNoOfPostsGreaterThan(Long count);
}
