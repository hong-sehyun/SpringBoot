package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDaoListimpl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {

	private MemberInterface memberDao;
	
	public MemberService() {
		memberDao = new MemberDaoListimpl();		
	}
	
	public MemberVO getMember(int id)  {
		return memberDao.getMember(id);
	}
	
	public List<MemberVO> getMembers()  {

		return memberDao.getMembers();

	}
	
	public MemberVO addMember(MemberVO member) {
		return memberDao.addMember(member);
	}
	
	public MemberVO updateMember(MemberVO member) {
		return memberDao.updateMember(member);
	}
	
	public int deleteMember(int id) {
		return memberDao.deleteMember(id);
	}
}
