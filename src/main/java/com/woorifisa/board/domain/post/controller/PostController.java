package com.woorifisa.board.domain.post.controller;

import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woorifisa.board.common.dto.response.CommonResponse;
import com.woorifisa.board.common.dto.session.MemberSession;
import com.woorifisa.board.domain.post.dto.request.PostRequest;
import com.woorifisa.board.domain.post.dto.response.PostResponse;
import com.woorifisa.board.domain.post.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping
	public ResponseEntity<CommonResponse<PostResponse>> create(
		// @Login(role = "ADMIN") MemberSession memberSession,
		MemberSession memberSession,
		@RequestBody @Valid PostRequest postRequest) {

		PostResponse post = postService.createPost(memberSession, postRequest);

		return CommonResponse.success(CREATED, CREATED.value(), post);
	}

	@GetMapping("/{postId}")
	public void retrievePost() {

	}

	@GetMapping
	public void retrievePosts() {

	}

	@PutMapping
	public void update() {

	}

	@DeleteMapping
	public void delete() {

	}

}
