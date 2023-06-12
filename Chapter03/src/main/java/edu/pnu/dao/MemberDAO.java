package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.Member;

public class MemberDAO {
	
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/test";
	private String username = "sa";
	private String password = "tiger";
	
	private Connection con;
	public MemberDAO() {
		try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, username, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void insertMember(Member m) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate(String.format("insert into member(name, age, nickname) values ('%s','%d','%s')", m.getName(), m.getAge(), m.getNickname()));
			st.close();			
			} catch (Exception e) {
				e.printStackTrace();
			}	
	}
	
	public List<Member> getMembers() {
		List<Member> list = new ArrayList<>();
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(String.format("select*from member order by id"));
		
		
		
		while(rs.next()) {
		list.add(Member.builder()
				.id(rs.getLong("id"))
				.name(rs.getString("name"))
				.age(rs.getInt("age"))
				.nickname(rs.getString("nickname"))
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
	
	public Member getMember(long id) {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(String.format("select*from member order by id"));
			
			rs.next();
			
			Member m = Member.builder()
					.id(rs.getLong("id"))
					.name(rs.getString("name"))
					.age(rs.getInt("age"))
					.nickname(rs.getString("nickname"))
					.build();
			
			rs.close();
			st.close();
			
			return m;
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
}
