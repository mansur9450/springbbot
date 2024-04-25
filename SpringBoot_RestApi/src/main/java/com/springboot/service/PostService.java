package com.springboot.service;

import java.util.List;

import com.springboot.payload_dto.PostDto;
import com.springboot.payload_dto.PostResponse;

public interface PostService {

PostDto createPost(PostDto postDto);
PostDto getpostbyid(long id);
PostDto updatePost(PostDto postDto, long id);
public void deletePostByid(long id);
PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

// here only dto layer bcz convert entity and save in database

}
