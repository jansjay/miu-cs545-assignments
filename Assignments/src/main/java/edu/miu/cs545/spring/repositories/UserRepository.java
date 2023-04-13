package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM users u WHERE u.id IN (SELECT p.user_id FROM post p GROUP BY p.user_id HAVING count(*) > :count)", nativeQuery = true)
    //@Query(value = "SELECT u FROM User u WHERE COUNT(u.posts) > :count")
    Collection<User> getUsersWithNoOfPostsGreaterThan(Long count);
    Collection<User> findByPostsTitleIgnoreCase(String title);

    Optional<User> findByName(String username);
}
