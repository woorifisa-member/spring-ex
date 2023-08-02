package com.woorifisa.board.domain.post.dto.response;

import java.time.LocalDateTime;

import com.woorifisa.board.domain.post.entity.Post;

import lombok.Getter;

@Getter
public class PostResponse {

	private final Long id;
	private final String title;
	private final String content;
	private final String email;
	private final LocalDateTime createdAt;

	public PostResponse(Long id, String title, String content, String email, LocalDateTime createdAt) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.email = email;
		this.createdAt = createdAt;
	}

	public static PostResponse fromEntity(Post post) {
		return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getMember().getEmail(),
			post.getCreatedAt());
	}

}
