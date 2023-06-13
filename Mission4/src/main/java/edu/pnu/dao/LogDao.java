package edu.pnu.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.LogVO;

public class LogDao {
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/mission3";
	private String username = "scott";
	private String password = "tiger";

	private Connection con;

	public LogDao() {

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public LogVO getLog(int id) {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(String.format("select*from dblog order by id"));
			
			rs.next();
			
			LogVO log = LogVO.builder()
					.id(rs.getInt("id"))
					.method(rs.getString("method"))
					.sqlstring(rs.getString("sqlstring"))
					.regidate(rs.getDate("regidate"))
					.success(rs.getBoolean("success"))
					.build();
					
			
			rs.close();
			st.close();
			
			return log;
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	
	public List<LogVO> getLogs() {
		List<LogVO> list = new ArrayList<>();
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(String.format("select*from dblog order by id"));
		
		while(rs.next()) {
		list.add(LogVO.builder()
				.id(rs.getInt("id"))
				.method(rs.getString("method"))
				.sqlstring(rs.getString("sqlstring"))
				.regidate(rs.getDate("regidate"))
				.success(rs.getBoolean("success"))
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
	
	public LogVO addLog(LogVO log) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate(String.format("insert into dblog(method, sqlstring) values ('%s','%s')", log.getMethod(), log.getSqlstring()));
			
//			for(int i = 1; i <= 10; i++) {
//				member.setName("name" + i);
//				member.setPass("pass"+i);
//			
//			st.close();
//						
//			return member;
//			}
			st.close();			
			return log;
			
			} catch (Exception e) {
				e.printStackTrace();
			}	
		return log;

	}
	

	
}
