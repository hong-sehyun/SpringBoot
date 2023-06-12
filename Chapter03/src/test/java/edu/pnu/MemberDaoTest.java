package edu.pnu;
import java.util.List;

//import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.Member;
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MemberDaoTest {
	
	@Test
	@Order(1)
	public void insertMemberTest() {
		MemberDAO memberDao = new MemberDAO();
		
		for(int i = 1; i <= 10; i++) {
			//Builder를 이용한 방법
			memberDao.insertMember(
					Member.builder()
					.name("name" + i)
					.age(20+i)
					.nickname("nickname"+i)
					.build()
					);

			//기본 생성자를 이용한 방법
//			Member m = new Member();
//			m.setName("name" + i);
//			m.setAge(20+i);
//			m.setNickname("nickname"+i);
//			memberDao.insertMember(m);
			
			//파라미터
//			memberDao.insertMember(new Member(-1L, "name"+i, 20 + i, "nickname"+i));
		}
		
	
	}

	@Test
	@Order(2)
	public void selecteAllMemberTest() {
		MemberDAO memberDao = new MemberDAO();
		List<Member> list = memberDao.getMembers();
		for (Member m : list) {
			System.out.println(m);
		}
	}

}
