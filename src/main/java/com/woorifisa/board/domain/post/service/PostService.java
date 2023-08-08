package com.woorifisa.board.domain.post.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.woorifisa.board.common.dto.response.PageResponse;
import com.woorifisa.board.common.dto.session.MemberSession;
import com.woorifisa.board.domain.post.dto.request.PostRequest;
import com.woorifisa.board.domain.post.dto.response.PostResponse;

public interface PostService {

	PostResponse createPost(MemberSession memberSession, PostRequest postRequest);

	PostResponse retrievePost(Long id);

	PageResponse<PostResponse> retrievePosts(Pageable pageable);

	List<PostResponse> retrieveAll();

	PostResponse updatePost(PostRequest postRequest);

	boolean deletePost(Long id);

}
