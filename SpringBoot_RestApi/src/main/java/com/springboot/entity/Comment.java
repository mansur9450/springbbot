package com.springboot.entity;

	
	import javax.persistence.*;
	
	@Entity
	@Table(name="comments")
	public class Comment {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;

	    @ManyToOne(fetch = FetchType.LAZY)// many comment in one post, lazy bcz it load code depand on requairment
	    @JoinColumn(name="post_id",nullable = false)// foreign key
	    private Post post;

	  
	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }


	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public Post getPost() {
	        return post;
	    }

	    public void setPost(Post post) {
	        this.post = post;
	    }
	}


