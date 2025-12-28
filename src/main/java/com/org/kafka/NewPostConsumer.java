package com.org.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.org.model.Post;

@Service
public class NewPostConsumer {
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	@KafkaListener(topics = "post-topic-ordered", groupId = "ordered-post-consumer-group")

	 public void consume(Post post) {

        jdbcTemplate.update(
            "CALL sp_insert_post(?, ?, ?)",
            post.getId(),
            post.getTitle(),
            post.getBody()
        );
}
}