package edu.miu.cs545.spring.services;

import edu.miu.cs545.spring.dto.CommentDto;
import edu.miu.cs545.spring.models.Comment;
import edu.miu.cs545.spring.models.Post;
import edu.miu.cs545.spring.repositories.CommentRepository;
import edu.miu.cs545.spring.repositories.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommentDto addComment(Long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            throw new EntityNotFoundException("Post not found.");
        }
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        commentRepository.save(comment);
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public Collection<CommentDto> findComments(Long userId, Long postId) {
        Collection<Post> postCollection = postRepository.findPostsByUserIdPostId(userId, postId);
        if (postCollection == null || postCollection.size() < 1) {
            throw new EntityNotFoundException("Post not found.");
        }
        return postCollection.stream().toList().get(0).getComments().stream().map(x -> modelMapper.map(x, CommentDto.class)).toList();
    }

    @Override
    public CommentDto findComment(Long userId, Long postId, Long commentId) {
        Collection<Post> postCollection = postRepository.findPostsByUserIdPostId(userId, postId);
        if (postCollection == null || postCollection.size() < 1) {
            throw new EntityNotFoundException("Post not found.");
        }
        return modelMapper.map(commentRepository.findCommentByPostIdAndCommentId(postId, commentId), CommentDto.class);
    }
}
