package practice.jpa.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.jpa.domain.member.entity.Member;
import practice.jpa.domain.member.repository.MemberRepository;

@RequiredArgsConstructor
public class MemberServiceImpl {
    private final MemberRepository memberRepository;

    public Member save(Member member) {
        return memberRepository.save(member);
    }


}
