package edu.pnu.dao;

import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public abstract class MemberDaoListimpl implements MemberInterface {
	
	private List<MemberVO> list;
	
	@Override
	public List<MemberVO> getMembers() {
		
		return list;
		
	}
	
	public MemberVO getMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
	
	public MemberVO addMember(MemberVO member) {
		member.setId(list.size() + 1);
		member.setRegidate(new Date());
		list.add(member);
		return member;
	}
	
	public MemberVO updateMember(MemberVO member) {
		for (MemberVO m : list) {
			if (m.getId() == member.getId()) {
				m.setName(member.getName());
				m.setPass(member.getPass());
				return m;
			}
		}
		return null;
	}
	
	public int deleteMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				return 1;
			}
		}
		return -1;
	}
	

}
