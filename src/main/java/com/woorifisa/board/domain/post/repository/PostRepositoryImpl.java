package com.woorifisa.board.domain.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.woorifisa.board.domain.post.entity.Post;

public class PostRepositoryImpl implements PostRepositoryCustom {

	@Override
	public Page<Post> findPosts(Pageable pageable) {
		return null;
	}

}
