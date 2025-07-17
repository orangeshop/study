package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    void save(Member m) {
        memberRepository.save(m);
    }

    List<MemberDTO> getList(){
        List<Member> members = memberRepository.findAll();
        return members.stream().map(MemberDTO::from).collect(Collectors.toList());

    }


    @Builder
    static class MemberDTO{
        String name;
        String email;

        public static MemberDTO from(Member member) {
            return MemberDTO.builder().name(member.getName()).email(member.getEmail()).build();
        }
    }
}
