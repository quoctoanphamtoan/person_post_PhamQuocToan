package com.quoctoanphamtoan.person_post.service.impl;

import com.quoctoanphamtoan.person_post.common.ApiResponse;
import com.quoctoanphamtoan.person_post.common.BadRequestException;
import com.quoctoanphamtoan.person_post.common.PaginationMeta;
import com.quoctoanphamtoan.person_post.data.PostData;
import com.quoctoanphamtoan.person_post.dto.UserDto;
import com.quoctoanphamtoan.person_post.entity.Post;
import com.quoctoanphamtoan.person_post.entity.User;
import com.quoctoanphamtoan.person_post.repository.IUserRepository;
import com.quoctoanphamtoan.person_post.service.IUserService;
import com.sun.deploy.security.UserDeclinedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserRepository userRepository;
    @Override
    public ApiResponse insert(UserDto userDto) {
        if(checkUserNameExist(userDto.getUserName())==false){
            throw  new UserDeclinedException("User name exist!");
        }
        System.out.println(userDto);
        ApiResponse apiResponse = new ApiResponse();
        String hasedPass = BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt());
        User user = new User();
        user.setName(userDto.getName());
        user.setUserName(userDto.getUserName());
        user.setPassword(hasedPass);
        user.setRoleId(1);
        apiResponse.setData(userRepository.save(user));
        return apiResponse;
    }

    @Override
    public ApiResponse login(UserDto userDto) {
        ApiResponse apiResponse = new ApiResponse();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
        Date now = new Date();
        String token = Jwts.builder()
                .setSubject(userDto.getUserName())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 86400000L))
                .signWith(SignatureAlgorithm.HS512, "ABC_EGH")
                .compact();
        Map<String,Object> rs= new HashMap<>();
        User user = userRepository.findUserByUserName(userDto.getUserName());
        UserDto dto = new UserDto();
        dto.setUserName(user.getUserName());
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        rs.put("token",token);
        rs.put("user",dto);
        apiResponse.setData(rs);
        return apiResponse;
    }

    @Override
    public boolean checkUserNameExist(String userName) {
        User user = userRepository.findUserByUserName(userName);
        if (user==null){
            return true;
        }
        return false;
    }

    @Override
    public ApiResponse editProfile(UserDto userDto) {
        return null;
    }
}
