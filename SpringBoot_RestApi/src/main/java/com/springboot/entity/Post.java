package com.springboot.entity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    @Column(name = "title", nullable = false,unique=true)
    private String title;
    @Column(name = " description", nullable = false)
    private String description;
    @Column(name = " content", nullable = false)
    private String content;
    
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)// it present in comment sec,cascade means if you make 
    // any change in parent then its effect in also child class
   private  Set<Comment> comments = new HashSet<>();//set bcz only unique comment
   
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
