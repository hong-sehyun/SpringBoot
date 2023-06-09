package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.Member;

public class MemberService {

	private MemberDAO memberDAO;
	public MemberService() {
		memberDAO = new MemberDAO();
//		list = new ArrayList<>();
		
	}
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
