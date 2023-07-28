package com.woorifisa.board.domain.post.service;

import com.woorifisa.board.domain.post.dto.request.PostRequest;
import com.woorifisa.board.domain.post.dto.response.PostResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface PostService {

    PostResponse createPost(PostRequest postRequest);

    PostRequest retrievePost(Long id);

    List<PostRequest> retrievePosts(Pageable pageable);

    PostResponse updatePost(PostRequest postRequest);

    boolean deletePost(Long id);

}
