package com.quoctoanphamtoan.person_post.api;

import com.quoctoanphamtoan.person_post.common.ApiResponse;
import com.quoctoanphamtoan.person_post.dto.PostDto;
import com.quoctoanphamtoan.person_post.entity.Post;
import com.quoctoanphamtoan.person_post.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
public class PostApi {
    @Autowired
    private IPostService postService;
    @GetMapping("/by-person/{personId}")
    public ApiResponse getPostByPerson(@PathVariable("personId") long personId,@SortDefault Pageable pageable){
        return postService.listOfPerson(personId,pageable);
    }
    @PostMapping("")
    public ApiResponse add(@RequestBody Post post) {
        return postService.add(post);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletePost(@PathVariable("id") Long id){
        return postService.delete(id);
    }
    @PutMapping("/{id}")
    public ApiResponse editPost(@PathVariable("id") Long id, @RequestBody PostDto postDto){
        return postService.edit(id,postDto);
    }
}
