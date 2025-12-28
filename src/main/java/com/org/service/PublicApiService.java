package com.org.service;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.org.kafka.PostProducer;
import com.org.model.Post;
import com.org.model.PostFetchRequest;

@Service
public class PublicApiService {

	private final RestTemplate restTemplate = new RestTemplate();
    private final PostProducer producer;

    public PublicApiService(PostProducer producer) {
        this.producer = producer;
    }

    public void fetchAndPublish(PostFetchRequest request) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl("https://jsonplaceholder.typicode.com/posts")
                .queryParam("fromDate", request.getFromDate())
                .queryParam("toDate", request.getToDate())
                .queryParam("userType", request.getUserType())
                .build()
                .toUri();

        Post[] posts = restTemplate.getForObject(uri, Post[].class);

        List<Post> postList = Arrays.asList(posts);

        //  Publish each record to Kafka
        postList.forEach(producer::send);
    }
	
}
