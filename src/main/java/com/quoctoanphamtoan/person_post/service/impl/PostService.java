package com.quoctoanphamtoan.person_post.service.impl;

import com.quoctoanphamtoan.person_post.common.ApiResponse;
import com.quoctoanphamtoan.person_post.common.BadRequestException;
import com.quoctoanphamtoan.person_post.common.PaginationMeta;
import com.quoctoanphamtoan.person_post.data.PostData;
import com.quoctoanphamtoan.person_post.dto.PostDto;
import com.quoctoanphamtoan.person_post.entity.Post;
import com.quoctoanphamtoan.person_post.entity.User;
import com.quoctoanphamtoan.person_post.repository.IPostRepository;
import com.quoctoanphamtoan.person_post.repository.IUserRepository;
import com.quoctoanphamtoan.person_post.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IUserRepository userRepository;
    @Override
    public ApiResponse list(Pageable pageable){
        ApiResponse apiResponse = new ApiResponse();
        Page<Post> postPage = postRepository.findAll(pageable);
        List<PostDto>  posts =  new ArrayList<>();
        postPage.getContent().forEach(x->{
            posts.add(new PostDto(x.getId(),x.getTitle(),x.getContent(),x.getCreatedBy().getId()));
        });
        PaginationMeta postPaginationMeta = PaginationMeta.createPagination(postPage);
        PostData postData = new PostData();
        postData.setPosts(posts);
        postData.setPaginationMeta(postPaginationMeta);
        apiResponse.setData(postData);
        return apiResponse;

    }

    @Override
    public ApiResponse add(Post post) {
        ApiResponse apiResponse = new ApiResponse();
        Post postRs =  postRepository.save(post);
        apiResponse.setData(new PostDto(postRs.getId(),postRs.getTitle(),postRs.getContent(),postRs.getCreatedBy().getId()));
        return apiResponse;
    }

    @Override
    public ApiResponse listOfPerson(Long personId,Pageable pageable) {
        ApiResponse apiResponse = new ApiResponse();
        Page<Post> postPage = postRepository.findAll(pageable);
        List<PostDto>  posts =  new ArrayList<>();
        postPage.getContent().forEach(x->{
           if(x.getCreatedBy().getId()==personId){
               posts.add(new PostDto(x.getId(),x.getTitle(),x.getContent(),x.getCreatedBy().getId()));
           }
        });
        PaginationMeta postPaginationMeta = PaginationMeta.createPagination(postPage);
        PostData postData = new PostData();
        postData.setPosts(posts);
        postData.setPaginationMeta(postPaginationMeta);
        apiResponse.setData(postData);
        return apiResponse;
    }

    @Override
    public ApiResponse delete(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        Post postDelete = postRepository.getById(id);

        User user =  userRepository.findUserByUserName(username);
        if(user.getId()!=postDelete.getCreatedBy().getId()){
            throw  new BadRequestException("errr",null);
        }


        /////
        ApiResponse apiResponse = new ApiResponse();
        postRepository.delete(postDelete);
        apiResponse.setData("Delete success");
        return apiResponse;
    }

    @Override
    public ApiResponse edit(Long id, PostDto postDto) {
        Post update =  postRepository.findById(id).map(post->{
            post.setTitle(postDto.getTitle());
            post.setContent(postDto.getContent());
            return postRepository.save(post);
        }).orElse(null);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(update);
        return apiResponse;
    }
}
