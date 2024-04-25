package com.springboot.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.el.stream.Stream;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Comment;
import com.springboot.entity.Post;
import com.springboot.exception.ResourceNotFound;
import com.springboot.payload_dto.CommentDto;
import com.springboot.repository.CommentRepository;
import com.springboot.repository.PostRepository;
import com.springboot.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
@Autowired
private CommentRepository commentRepo;
@Autowired
private PostRepository postrepo;
@Autowired
private ModelMapper mapper;

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		
        Comment comment = mapToEntity(commentDto);
        postrepo.findById(postId).orElseThrow( () -> new ResourceNotFound("Post", "id", postId));
        Comment newcomment = commentRepo.save(comment);
        
		return mapToDTO(newcomment);
	}
	
	private CommentDto mapToDTO(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);

       // CommentDto commentDto1 = new CommentDto();
       // commentDto1.setId(comment.getId());
        //commentDto1.setName(comment.getName());
        //commentDto1.setEmail(comment.getEmail());
       // commentDto1.setBody(comment.getBody());
        return  commentDto;
    }


    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
       //Comment comment1 = new Comment();
      // comment1.setName(commentDto.getName());
      // comment1.setEmail(commentDto.getEmail());
       // comment1.setBody(commentDto.getBody());
       // comment1.setId(commentDto.getId());
        return  comment;
    }

	@Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        // retrieve comments by postId
        List<Comment> comments = commentRepo.findByPostId(postId);

        // convert list of comment entities to list of comment dto's
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

	}


