package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.models.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Query("SELECT comment FROM Comment comment WHERE comment.post.id = :postId AND comment.id = :commentId")
    Comment findCommentByPostIdAndCommentId(@Param("postId") Long postId, @Param("commentId") Long commentId);
}
