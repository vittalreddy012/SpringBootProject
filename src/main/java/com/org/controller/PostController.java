package com.org.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.model.PostFetchRequest;
import com.org.service.PublicApiService;

@RestController
public class PostController {

	 private final PublicApiService apiService;

	    public PostController(PublicApiService apiService) {
	        this.apiService = apiService;
	    }

	    @GetMapping("/ingest-posts")
	    public String ingestPosts(
	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	                    LocalDate fromDate,

	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	                    LocalDate toDate,

	            @RequestParam String userType
	    ) {
	        PostFetchRequest request = new PostFetchRequest();
	        request.setFromDate(fromDate);
	        request.setToDate(toDate);
	        request.setUserType(userType);

	        apiService.fetchAndPublish(request);

	        return "Data fetched and published to Kafka";
	    }
}
