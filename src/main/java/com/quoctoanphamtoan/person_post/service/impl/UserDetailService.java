package com.quoctoanphamtoan.person_post.service.impl;

import com.quoctoanphamtoan.person_post.dto.UserDetail;
import com.quoctoanphamtoan.person_post.entity.User;
import com.quoctoanphamtoan.person_post.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("user ko ton tai");
        }
        String roleName = user.getRole().getName();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(roleName));
        return new UserDetail(username, user.getPassword(), authorities,user.getUserName());
    }
}
