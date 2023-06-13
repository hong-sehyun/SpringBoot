package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoH2impl {
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/mission3";
	private String username = "scott";
	private String password = "tiger";

	private Connection con;
	
	public MemberDaoH2impl() {
		
		try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, username, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public MemberVO getMember(int id) {
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(String.format("select*from member order by id"));
		
		rs.next();
		
		MemberVO m = MemberVO.builder()
				.id(rs.getInt("id"))
				.pass(rs.getString("pass"))
				.name(rs.getString("name"))
				.regidate(rs.getDate("regidate"))
				.build();
		
		rs.close();
		st.close();
		
		return m;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<MemberVO> getMembers() {
		List<MemberVO> list = new ArrayList<>();
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(String.format("select*from member order by id"));
		
		while(rs.next()) {
		list.add(MemberVO.builder()
				.id(rs.getInt("id"))
				.pass(rs.getString("pass"))
				.name(rs.getString("name"))
				.regidate(rs.getDate("regidate"))
				.build());
		}
		
		rs.close();
		st.close();
		
		return list;
		
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return null;
	}
	
	public MemberVO addMember(MemberVO member) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate(String.format("insert into member(pass, name) values ('%s','%s')", member.getPass(), member.getName()));
			
//			for(int i = 1; i <= 10; i++) {
//				member.setName("name" + i);
//				member.setPass("pass"+i);
//			
//			st.close();
//						
//			return member;
//			}
			st.close();			
			return member;
			
			} catch (Exception e) {
				e.printStackTrace();
			}	
		return member;
	}
	
	public MemberVO updateMember(MemberVO member) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate(String.format("update member set pass='%s', name='%s' where id=%d", member.getPass(), member.getName(), member.getId()));
			st.close();

			return member;
			
			} catch (Exception e) {
				e.printStackTrace();
			}	
		return member;
	
	}
	
	public int deleteMember(int id) {
		
		try {
			Statement st = con.createStatement();
			int ret = st.executeUpdate(String.format("delete member where id=%d", id));
			st.close();

			return ret;
			
			} catch (Exception e) {
				e.printStackTrace();
			}	
		return 0;
		
	}
	
	

}