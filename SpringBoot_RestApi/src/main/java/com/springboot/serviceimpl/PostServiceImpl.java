package com.springboot.serviceimpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Post;
import com.springboot.exception.ResourceNotFound;
import com.springboot.payload_dto.PostDto;
import com.springboot.payload_dto.PostResponse;
import com.springboot.repository.PostRepository;
import com.springboot.service.PostService;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postrepository;
	@Autowired
	private ModelMapper mapper;


	@Override
	public PostDto createPost(PostDto postDto) {
		 // Convert Dto--- Entity
		Post post = mapToEntity(postDto);
		Post newpost =  postrepository.save(post);
		
		// convert entity to dto
		PostDto postresponse = mapToDto(newpost);
		return postresponse;
	}

	private PostDto mapToDto(Post post) {// convert entity to dto
		PostDto postDto = mapper.map(post, PostDto.class);
		// it convert entity to dto, so we use mapper in place of set and get
		//it is easy
		
		//--- its bean create in main method myself
		
		//PostDto postDto = new PostDto();
		//postDto.setId(post.getId());// bcz id not auto in dto class
		//postDto.setTitle(post.getTitle());
		//postDto.setDescription(post.getDescription());
		//postDto.setContent(post.getContent());
		
		return postDto;
	}

	private Post mapToEntity(PostDto postDto) {// convert dto to entity
		Post post = mapper.map(postDto, Post.class);
		// it convert dto into entity so we use mapper in place of set and get
		
		
		//Post post = new Post();
		//post.setId(postDto.getId());
		//post.setTitle(postDto.getTitle());
		//post.setDescription(postDto.getDescription());
		//post.setContent(postDto.getContent());
		return post;
	}

    

	@Override
	public PostDto getpostbyid(long id) {
	Post post =	postrepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post", "id", id));
		return mapToDto(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, long id) {
        // get post by id from the database
        Post post = postrepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postrepository.save(post);
        return mapToDto(updatedPost);
    }

	@Override
	public void deletePostByid(long id) {
		Post post = postrepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post", "id", id));
		 postrepository.delete(post);
	}

	

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir) {
		Pageable pageable = PageRequest.of(pageSize, pageNo, Sort.by(sortBy));
        Page<Post> posts = postrepository.findAll(pageable);
        List<Post> content = posts.getContent();// convert in list
         List<PostDto> contents = content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
  PostResponse postResponse = new PostResponse();
  postResponse.setContent(contents);
  postResponse.setPageNo(posts.getNumber());
  postResponse.setPageSize(posts.getSize());
  postResponse.setTotalPages(posts.getTotalPages());
  postResponse.setTotalElement(posts.getTotalElements());
  postResponse.setLast(posts.isLast());
  
         return postResponse;
	}
	}

	
	
	
	

	
