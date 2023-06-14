package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor //4번
public class MemberController {
	// 1번 방법 필드에서 설정하는 방법
//	@Autowired
	// 4번 private MemberService memberService; 에 final을 붙여서 사용. lombok 어노테이션을 이용한 방법
	private final MemberService memberService;
	
	// 2번 생성자에서 설정하는 방법
//	@Autowired
//	public MemberController() {
//
//		this.memberService = memberService;
//	}
	
	// 3번 setter를 이용한 방법
//	@Autowired
//	private void setMemberService(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	@GetMapping("/member/{id}")
	public Member getMember(@PathVariable long id ) {
		return memberService.getMember(id);
	}
	
	@GetMapping("/member")
	public List<Member>getMembers() {
		return memberService.getMembers();
	}
	
	@PostMapping("/member")
	public int insertMember(Member member) {
		return memberService.insertMember(member);
	}

	@PutMapping("/member")
	public int updateMember(Member member) {
		return memberService.updateMember(member);
	}
	@DeleteMapping("/member/{id}")
	public int deleteMember(@PathVariable long id ) {
		return memberService.deleteMember(id);
	}
	
}
