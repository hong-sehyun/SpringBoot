package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDaoListimpl;
import edu.pnu.domain.MemberVO;

public class MemberService {

	private MemberDaoListimpl memberDaolist;
	
	public MemberService() {
//		memberDaolist = new MemberDaoListimpl();		
	}
	
	public MemberVO getMember(int id)  {
		return memberDaolist.getMember(id);
	}
	
	public List<MemberVO> getMembers()  {

		return memberDaolist.getMembers();

	}
	
	public MemberVO addMember(MemberVO member) {
		return memberDaolist.addMember(member);
	}
	
	public MemberVO updateMember(MemberVO member) {
		return memberDaolist.updateMember(member);
	}
	
	public int deleteMember(Integer id) {
		return memberDaolist.deleteMember(id);
	}
}
