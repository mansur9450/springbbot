package com.springboot.service;

import java.util.List;

import com.springboot.payload_dto.CommentDto;

public interface CommentService {
	CommentDto createComment(long postId,CommentDto commentDto) ;
    List<CommentDto> getCommentsByPostId(long postId);
}
