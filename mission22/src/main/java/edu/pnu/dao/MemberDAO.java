package edu.pnu.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.pnu.domain.Member;


public class MemberDAO {
	

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

//	private String driver = "org.h2.Driver";
//	private String url = "jdbc:h2:tcp://localhost/~/mission2";
//	private String username = "scott";
//	private String password = "tiger";
//
//	private Connection con;
//	
//	public MemberDAO() {
//		
//		try {
//			Class.forName(driver);
//			
//			con = DriverManager.getConnection(url, username, password);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}

	public Member getMember(long id) {
		try {
			return jdbcTemplate.query("select*from member order by id", null);
		
		ResultSet rs;
		
		rs.next();
		
		Member m = Member.builder()
				.id(rs.getLong("id"))
				.pass(rs.getString("pass"))
				.name(rs.getString("name"))
				.regidate(rs.getDate("regidate"))
				.build();
		
		rs.close();

		
		return m;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Member> getMembers() {
		List<Member> list = new ArrayList<>();
		try {
		Statement st = createStatement();
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
			Statement st = con.createStatement();
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
			Statement st = con.createStatement();
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
