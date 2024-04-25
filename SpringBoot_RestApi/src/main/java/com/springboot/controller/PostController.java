package com.springboot.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.payload_dto.PostDto;
import com.springboot.payload_dto.PostResponse;
import com.springboot.service.PostService;
@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	private PostService postservice;
    @PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>( bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>( postservice.createPost(postDto), HttpStatus.CREATED);
		
	}
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false)  int pageNo,
@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ){
        return postservice.getAllPosts(pageNo, pageSize,sortBy,sortDir);
    }

	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getpostbyid(@PathVariable("id") long id){
		return ResponseEntity.ok(postservice.getpostbyid(id));
	}
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid@RequestBody PostDto postDto, @PathVariable(name = "id") long id){

       PostDto postResponse = postservice.updatePost(postDto, id);

       return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
@DeleteMapping("/{id}")
public ResponseEntity<String> deletePost(@PathVariable("id") long id){
	postservice.deletePostByid(id);
	return ResponseEntity.status(HttpStatus.OK).body("Post with ID " + id + " has been deleted successfully.");
}






}