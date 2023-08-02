package edu.pnu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {
	@Autowired
	private MemberRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> opt = repository.findByUsername(username); 
		if (!opt.isPresent()) {
			throw new UsernameNotFoundException("User not found.");
		}		
		Member m = opt.get();
		return new User(m.getUsername(), m.getPassword(), m.getAuthorities());
	}
}