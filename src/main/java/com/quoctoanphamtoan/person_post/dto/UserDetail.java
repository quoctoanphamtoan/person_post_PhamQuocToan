package com.quoctoanphamtoan.person_post.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetail extends User {
    private String name;
    public UserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities,String name) {
        super(username, password, authorities);
        this.name = name;

    }
}
