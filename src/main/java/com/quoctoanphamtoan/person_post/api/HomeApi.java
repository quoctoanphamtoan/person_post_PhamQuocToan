package com.quoctoanphamtoan.person_post.api;

import com.quoctoanphamtoan.person_post.common.ApiResponse;
import com.quoctoanphamtoan.person_post.entity.Post;
import com.quoctoanphamtoan.person_post.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
public class HomeApi {
    @Autowired
    private IPostService postService;


    @GetMapping("")
    public ApiResponse list(@SortDefault Pageable pageable) {
        ApiResponse apiResponse = postService.list(pageable);
        return apiResponse;
    }
}
