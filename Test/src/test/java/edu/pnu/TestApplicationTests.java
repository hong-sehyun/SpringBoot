package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
class TestApplicationTests {

	@Autowired
	private MemberRepository memRepo;

	@Test
	void contextLoads() {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		memRepo.save(Member.builder().username("member").password(encoder.encode("abcd")).role("ROLE_MEMBER").enabled(true).build());
	}


}
