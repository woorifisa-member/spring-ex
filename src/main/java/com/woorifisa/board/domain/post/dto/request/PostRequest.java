package com.woorifisa.board.domain.post.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostRequest {

	@NotBlank
	private String title;

	@NotBlank
	private String content;

}
