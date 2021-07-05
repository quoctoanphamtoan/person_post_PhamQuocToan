package com.quoctoanphamtoan.person_post.filter;

import com.quoctoanphamtoan.person_post.dto.UserDetail;
import com.quoctoanphamtoan.person_post.service.impl.UserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends BasicAuthenticationFilter {
    @Autowired
    private UserDetailService userDetailService;
    public AuthFilter(AuthenticationManager authenticationManager,UserDetailService userDetailService) {
        super(authenticationManager);
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (request.getServletPath().startsWith("/api") && (token == null || token.isEmpty() || !token.startsWith("Bearer "))) {
            chain.doFilter(request, response);
            return;
        }
        String tokenXL = token.replace("Bearer ", "");
        String name = null;
        try {
            name = Jwts.parser().setSigningKey("ABC_EGH").parseClaimsJws(tokenXL).getBody().getSubject();
        } catch (Exception e) {
            if (e instanceof ExpiredJwtException) {
                response.sendError(401, "Hết hạn token");
            }
            return;
        }
        UserDetails userDetails;
        try {
            userDetails = userDetailService.loadUserByUsername(name);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            response.sendError(401, e.getMessage());
            return;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
