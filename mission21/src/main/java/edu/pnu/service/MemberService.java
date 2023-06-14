package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.Member;


@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
//	public MemberService() {
//		memberDAO = new MemberDAO();
//		
//	}
	public Member getMember(long id)  {
		return memberDAO.getMember(id);
	}
	public List<Member> getMembers()  {

		return memberDAO.getMembers();

	}
	public int updateMember(Member member)  {
		return memberDAO.updateMember(member);
	}
	
	public int insertMember(Member member) {
		return memberDAO.insertMember(member);
	}
	
	public int deleteMember(long id) {
		return memberDAO.deleteMember(id);
	}
	
}
