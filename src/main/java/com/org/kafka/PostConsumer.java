package com.org.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.org.dao.PostDao;
import com.org.model.Post;

@Service
public class PostConsumer {

	 private final PostDao postDao;

	    public PostConsumer(PostDao postDao) {
	        this.postDao = postDao;
	    }

	    @KafkaListener(
	        topics = "post-topic",
	        groupId = "post-consumer-group"
	    )
	    public void consume(Post post) {

	        System.out.println("Consuming post ID: " + post.getId());

	        //  Call stored procedure
	        postDao.insertPost(post);
	    }
}
