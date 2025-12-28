package com.org.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.org.model.Post;

@Service

public class NewPostProducer {

	private static final String TOPIC = "post-topic-ordered";

	    private final KafkaTemplate<String, Post> kafkaTemplate;

	    public NewPostProducer(KafkaTemplate<String, Post> kafkaTemplate) {
	        this.kafkaTemplate = kafkaTemplate;
	    }

	    public void send(Post post) {
	        kafkaTemplate.send(TOPIC, String.valueOf(post.getId()), post);
	    }
}
