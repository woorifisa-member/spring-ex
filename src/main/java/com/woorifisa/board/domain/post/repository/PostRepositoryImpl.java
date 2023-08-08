package com.woorifisa.board.domain.post.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.woorifisa.board.domain.post.entity.Post;
import com.woorifisa.board.domain.post.entity.QPost;

public class PostRepositoryImpl implements PostRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public PostRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public Page<Post> findPosts(Pageable pageable) {
		QPost post = QPost.post;

		List<Post> posts = queryFactory
			.select(post)
			.from(post)
			.limit(pageable.getPageSize())
			.offset(pageable.getOffset())
			.orderBy(post.createdAt.desc())
			.fetch();

		Long totalPage = queryFactory
			.select(post.count())
			.from(post)
			.orderBy(post.createdAt.desc())
			.fetchFirst();

		return new PageImpl<>(posts, pageable, totalPage);
	}
}
