package edu.pnu.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.Member;

@Repository
public class MemberDAO {
	@Autowired
	private DataSource datasource;


	public Member getMember(long id) {
		try {
		Statement st = datasource.getConnection().createStatement();
		ResultSet rs = st.executeQuery(String.format("select*from member order by id"));
		
		rs.next();
		
		Member m = Member.builder()
				.id(rs.getLong("id"))
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
	
	public List<Member> getMembers() {
		List<Member> list = new ArrayList<>();
		try {
		Statement st = datasource.getConnection().createStatement();
		ResultSet rs = st.executeQuery(String.format("select*from member order by id"));
		
		
		
		while(rs.next()) {
		list.add(Member.builder()
				.id(rs.getLong("id"))
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
	
	public int insertMember(Member member) {
		try {
			Statement st = datasource.getConnection().createStatement();
			int ret = st.executeUpdate(String.format("insert into member(pass, name) values ('%s','%s')", member.getPass(), member.getName()));
			st.close();
			
			
			return ret;
			
			} catch (Exception e) {
				e.printStackTrace();
			}	
		return 0;
	}
	
	public int updateMember(Member member) {
		try {
			Statement st = datasource.getConnection().createStatement();
			int ret = st.executeUpdate(String.format("update member set pass='%s', name='%s' where id=%d", member.getPass(), member.getName(), member.getId()));
			st.close();

			return ret;
			
			} catch (Exception e) {
				e.printStackTrace();
			}	
		return 0;
	
	}
	
	public int deleteMember(long id) {
		
		try {
			Statement st = datasource.getConnection().createStatement();
			int ret = st.executeUpdate(String.format("delete member where id=%d", id));
			st.close();

			return ret;
			
			} catch (Exception e) {
				e.printStackTrace();
			}	
		return 0;
		
	}
	
	

}
