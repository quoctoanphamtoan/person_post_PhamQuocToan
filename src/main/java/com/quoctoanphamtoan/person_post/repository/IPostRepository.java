package com.quoctoanphamtoan.person_post.repository;

import com.quoctoanphamtoan.person_post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {

}
