package com.quoctoanphamtoan.person_post.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id","title","content"})
public class PostDto {
    private Long id ;
    private String title;
    private String content;
    private Long createById;

}
