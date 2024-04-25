package com.springboot.payload_dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;

import lombok.Data;

@Data
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min =2, message = "At least two character")
    private String title;
    @NotEmpty
    @Size(min =8, message = "At least two character")
    private String description;
    @NotEmpty
    private String content;
    
    private Set<CommentDto> comments;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
    
    
}
