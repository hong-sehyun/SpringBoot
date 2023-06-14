package edu.pnu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class Mission2ApplicationTests {

	@Autowired
	MemberService memberservice;
	
//	@Autowired
//	DataSource datasource;
//	
//	@Test
//	public void datasourceTest() throws SQLException {
//		Statement st = datasource.getConnection().createStatement();
//		ResultSet rs = datasource.executeQuery("select * from Member");
//		
//		while(rs.next()) {
//			
//		}
//	}
	
	
	@Test
	@Order(1)
	public void contextLoads() {
		System.out.println("=".repeat(40));
		List<Member> list = memberservice.getMembers();
		for(Member m : list ) {
			System.out.println(m);
		}
		System.out.println("=".repeat(40));
	}
	
	@Test
	@Order(2)
	public void contextLoads2() {
		System.out.println("=".repeat(40));
		Member m = memberservice.getMember(1);
		System.out.println(m);
		System.out.println("=".repeat(40));				
	}
	
	@Test
	@Order(3)
	public void contextLoads3() {
		System.out.println("=".repeat(40));
		System.out.println(memberservice.insertMember(new Member(6, "pass6", "name6", new Date())));
		System.out.println("-".repeat(40));
		List<Member> list = memberservice.getMembers();
		for(Member m : list) {
			System.out.println(m);
		}
		System.out.println("=".repeat(40));
	}
	
	

}
