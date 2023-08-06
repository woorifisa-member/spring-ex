package com.woorifisa.board.domain.post.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woorifisa.board.common.dto.session.MemberSession;
import com.woorifisa.board.domain.member.entity.Member;
import com.woorifisa.board.domain.member.exception.MemberNotFoundException;
import com.woorifisa.board.domain.member.repository.MemberRepository;
import com.woorifisa.board.domain.post.dto.request.PostRequest;
import com.woorifisa.board.domain.post.dto.response.PostResponse;
import com.woorifisa.board.domain.post.entity.Post;
import com.woorifisa.board.domain.post.repository.PostRepository;

@Transactional(readOnly = true)
@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final MemberRepository memberRepository;

	public PostServiceImpl(PostRepository postRepository, MemberRepository memberRepository) {
		this.postRepository = postRepository;
		this.memberRepository = memberRepository;
	}

	@Transactional
	@Override
	public PostResponse createPost(
		final MemberSession memberSession,
		final PostRequest postRequest) {

		Member member = memberRepository.findById(memberSession.getId())
			.orElseThrow(MemberNotFoundException::new);

		Post post = Post.builder()
			.member(member)
			.title(postRequest.getTitle())
			.content(postRequest.getContent())
			.build();

		Post savedPost = postRepository.save(post);

		return PostResponse.fromEntity(savedPost);
	}

	@Override
	public PostRequest retrievePost(Long id) {
		return null;
	}

	@Override
	public List<PostRequest> retrievePosts(Pageable pageable) {
		return null;
	}

	@Override
	public PostResponse updatePost(PostRequest postRequest) {
		return null;
	}

	@Override
	public boolean deletePost(Long id) {
		return false;
	}

}
