package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API")
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public void save(@PathVariable Member member) {
        memberService.save(member);
    }

    @GetMapping
    public List<MemberService.MemberDTO> getList(){
        return memberService.getList();
    }
}
