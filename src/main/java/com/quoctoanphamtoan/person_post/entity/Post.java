package com.quoctoanphamtoan.person_post.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post extends BaseEntity {

    @Column(name = "content",length = 3000 )
    private String content;
    @Column(name = "title",length = 100 )
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @CreatedBy
    private User createdBy;



}
