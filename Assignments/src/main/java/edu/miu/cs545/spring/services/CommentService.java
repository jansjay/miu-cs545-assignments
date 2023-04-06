package edu.miu.cs545.spring.services;

import edu.miu.cs545.spring.dto.CommentDto;

import java.util.Collection;

public interface CommentService {
    CommentDto addComment(Long postId, CommentDto commentDto);
    Collection<CommentDto> findComments(Long userId, Long postId);
    CommentDto findComment(Long userId, Long postId, Long commentId);
}
