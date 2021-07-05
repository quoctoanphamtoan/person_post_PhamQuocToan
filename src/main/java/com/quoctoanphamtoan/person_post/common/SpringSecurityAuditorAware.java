package com.quoctoanphamtoan.person_post.common;

import java.util.Optional;

import com.quoctoanphamtoan.person_post.entity.User;
import com.quoctoanphamtoan.person_post.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityAuditorAware implements AuditorAware<User> {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public Optional<User> getCurrentAuditor() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return Optional.ofNullable(userRepository.findUserByUserName(username));
	}
}