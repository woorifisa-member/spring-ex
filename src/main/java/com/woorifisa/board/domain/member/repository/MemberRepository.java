package com.woorifisa.board.domain.member.repository;

import com.woorifisa.board.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
