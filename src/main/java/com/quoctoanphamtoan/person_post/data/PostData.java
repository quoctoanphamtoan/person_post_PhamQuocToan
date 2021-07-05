package com.quoctoanphamtoan.person_post.data;

import com.quoctoanphamtoan.person_post.common.PaginationMeta;
import com.quoctoanphamtoan.person_post.dto.PostDto;
import com.quoctoanphamtoan.person_post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostData {
    private List<PostDto> posts;
    private PaginationMeta paginationMeta;

}
