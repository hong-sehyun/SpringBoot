package edu.pnu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

//@Slf4j
@RestController
public class MemberController {

	private MemberService memberService;
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	public MemberController() {
		memberService = new MemberService();
	}
	
	@GetMapping("/members")
	public List<MemberVO> getMembers() {
		log.info("getMembers()");
		return memberService.getMembers();
	}

	//@PathVariable 경로상에 있는 변수를 맵핑하겠다는 뜻. id를 받으면 모든 회원의 url을 만들게 될수도
	///localhost:8080/member/1
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		log.info("getMember()");
		return memberService.getMember(id);
	}
	
	//위와 같지만 localhost:8080/member?id=1 으로 url받음. 둘 중 맞고 틀린 건 없음.
	@GetMapping("/member")
	public MemberVO getMember1(Integer id) {
		log.info("getMember()");
		return memberService.getMember(id);
	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO member) {
		log.info("addMember()");
		return memberService.addMember(member);
	}

	@PutMapping("/member")
	public MemberVO updateMember(MemberVO member) {
		log.info("updateMember()");
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member/{id}")
	public MemberVO deleteMember(@PathVariable Integer id) {
		log.info("deleteMember()");
		return memberService.deleteMember(id);
	}
}

/*
c reate post (insert 문)
r ead get(select 문)
u pdate update (update 문)
d elete delete (delete 문)

*/