package com.quoctoanphamtoan.person_post.service;

import com.quoctoanphamtoan.person_post.common.ApiResponse;
import com.quoctoanphamtoan.person_post.dto.UserDto;

public interface IUserService {
    ApiResponse insert(UserDto userDto);
    ApiResponse login(UserDto userDto);
    boolean checkUserNameExist(String userName);
    ApiResponse editProfile(UserDto userDto);
}
