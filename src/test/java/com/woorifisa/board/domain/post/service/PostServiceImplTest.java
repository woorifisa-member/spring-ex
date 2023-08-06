package com.woorifisa.board.domain.post.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.woorifisa.board.common.dto.session.MemberSession;
import com.woorifisa.board.domain.member.entity.Member;
import com.woorifisa.board.domain.member.repository.MemberRepository;
import com.woorifisa.board.domain.post.dto.request.PostRequest;
import com.woorifisa.board.domain.post.dto.response.PostResponse;
import com.woorifisa.board.domain.post.entity.Post;
import com.woorifisa.board.domain.post.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

	@Mock
	PostRepository postRepository;

	@Mock
	MemberRepository memberRepository;

	@InjectMocks
	PostServiceImpl postService;

	@DisplayName("게시물 생성")
	@Test
	void testCreatePost() {
		Long id = 1L;
		String title = "타이틀";
		String content = "컨텐트";

		MemberSession memberSession = mock(MemberSession.class);
		PostRequest postRequest = mock(PostRequest.class);

		Member member = mock(Member.class);

		given(memberSession.getId()).willReturn(id);
		given(postRequest.getTitle()).willReturn(title);
		given(postRequest.getContent()).willReturn(content);
		given(memberRepository.findById(id)).willReturn(Optional.of(member));

		Post post = Post.builder()
			.member(member)
			.title(postRequest.getTitle())
			.content(postRequest.getContent())
			.build();

		given(postRepository.save(any(Post.class))).willReturn(post);

		PostResponse resp = postService.createPost(memberSession, postRequest);

		assertThat(resp.getTitle()).isEqualTo(title);
		assertThat(resp.getContent()).isEqualTo(content);

		then(memberRepository).should(times(1)).findById(id);
		then(postRepository).should(times(1)).save(any(Post.class));
	}

}