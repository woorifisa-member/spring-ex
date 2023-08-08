package com.woorifisa.board.domain.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woorifisa.board.common.security.PasswordEncoder;
import com.woorifisa.board.domain.member.dto.request.LoginRequest;
import com.woorifisa.board.domain.member.dto.request.SignupRequest;
import com.woorifisa.board.domain.member.dto.response.MemberResponse;
import com.woorifisa.board.domain.member.entity.Member;
import com.woorifisa.board.domain.member.exception.MemberNotFoundException;
import com.woorifisa.board.domain.member.exception.UnauthorizedException;
import com.woorifisa.board.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public MemberResponse signup(SignupRequest signupRequest) {

		String encryptedPassword = passwordEncoder.encode(signupRequest.getPassword());

		log.info("raw password = {}", signupRequest.getPassword());
		log.info("encoded password = {}", encryptedPassword);

		Member member = Member.builder()
			.email(signupRequest.getEmail())
			.password(encryptedPassword)
			.name(signupRequest.getName())
			.build();

		Member savedMember = memberRepository.save(member);

		log.info("saved Member = {}", savedMember);

		return new MemberResponse(savedMember.getId(), savedMember.getEmail(), savedMember.getName());
	}

	@Override
	public MemberResponse login(LoginRequest loginRequest) {

		Member member = memberRepository.findByEmail(loginRequest.getEmail())
			.orElseThrow(MemberNotFoundException::new);

		String rawPassword = loginRequest.getPassword();
		String encodedPassword = member.getPassword();

		if (passwordEncoder.matches(rawPassword, encodedPassword)) {
			return new MemberResponse(member.getId(), member.getEmail(), member.getName());
		}

		throw new UnauthorizedException();
	}

	@Transactional
	public void init() {
		String pwd = passwordEncoder.encode("1234");

		Member minsu = Member.builder()
			.email("dkrak3212@gmail.com")
			.password(pwd)
			.name("김민수")
			.build();

		Member sunju = Member.builder()
			.email("201902133@hufs.ac.kr")
			.password(pwd)
			.name("양선주")
			.build();

		Member dongyeol = Member.builder()
			.email("eastheat10@gmail.com")
			.password(pwd)
			.name("윤동열")
			.build();

		Member cheolcheol = Member.builder()
			.email("dream174822@gmail.com")
			.password(pwd)
			.name("황철원")
			.build();

		memberRepository.saveAll(List.of(minsu, sunju, dongyeol, cheolcheol));
	}

}
