package com.org.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.org.kafka.NewPostProducer;
import com.org.model.Post;

@Service
public class NewPostService {

	
	private final RestTemplate restTemplate = new RestTemplate();
    private final NewPostProducer producer;

    public NewPostService(NewPostProducer producer) {
        this.producer = producer;
    }

    public void fetchAndPublish() {

        Post[] posts = restTemplate.getForObject(
                "https://jsonplaceholder.typicode.com/posts",
                Post[].class
        );

        if (posts == null) return;

        List<Post> postList = Arrays.asList(posts);
        postList.forEach(post -> producer.send(post));
    }
}
