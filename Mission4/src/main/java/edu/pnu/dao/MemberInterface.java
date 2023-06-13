package edu.pnu.dao;

import java.util.List;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {
	
	List<MemberVO> getMembers();
	MemberVO getMember(int id);
	MemberVO addMember(MemberVO member);
	MemberVO updateMember(MemberVO member);
	int deleteMember(int id);
	

}
