package com.org.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.service.NewPostService;


@RestController
public class NewPostController {
	 private final NewPostService postService;

	    public NewPostController(NewPostService postService) {
	        this.postService = postService;
	    }

	    @GetMapping("/new-posts")
	    public String ingestPosts() {
	        postService.fetchAndPublish();
	        return "Data fetched and published to Kafka";
	    }
	
	
}
