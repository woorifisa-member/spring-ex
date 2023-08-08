package com.woorifisa.board.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woorifisa.board.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
