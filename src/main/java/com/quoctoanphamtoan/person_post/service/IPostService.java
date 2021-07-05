package com.quoctoanphamtoan.person_post.service;

import com.quoctoanphamtoan.person_post.common.ApiResponse;
import com.quoctoanphamtoan.person_post.dto.PostDto;
import com.quoctoanphamtoan.person_post.entity.Post;
import org.springframework.data.domain.Pageable;


public interface IPostService {
    ApiResponse list(Pageable pageable);
    ApiResponse add(Post post);
    ApiResponse listOfPerson(Long personId,Pageable pageable);
    ApiResponse delete(Long id);
    ApiResponse edit(Long id, PostDto postDto);
}
