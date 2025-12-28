package com.org.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.org.model.Post;
@Repository
public class PostDao {
	private final JdbcTemplate jdbcTemplate;

    public PostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertPost(Post post) {
        jdbcTemplate.update(
            "CALL sp_insert_post(?, ?, ?)",
            post.getId(),
            post.getTitle(),
            post.getBody()
        );
    }
}
