package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
class BeachinfoApplicationTests {
//	@Autowired
//	private MemberRepository memRepo;
//
//	
//	@Autowired
//	private BCryptPasswordEncoder encoder;
	
	
	
	@Test
	void contextLoads() {
//		memRepo.save(Member.builder().username("member").password(encoder.encode("abcd")).role("ROLE_MEMBER").build());
		System.out.println("Test");
	}

}
