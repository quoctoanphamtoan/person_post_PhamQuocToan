package com.quoctoanphamtoan.person_post.api;

import com.quoctoanphamtoan.person_post.common.ApiResponse;
import com.quoctoanphamtoan.person_post.dto.UserDto;
import com.quoctoanphamtoan.person_post.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthApi {
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ApiResponse register(@RequestBody UserDto userDto) {
        return userService.insert(userDto);
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }
}
