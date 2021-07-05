package com.quoctoanphamtoan.person_post.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"name","userName","password"})
public class UserDto {
    private String name;
    private String userName;
    private String password;

}
