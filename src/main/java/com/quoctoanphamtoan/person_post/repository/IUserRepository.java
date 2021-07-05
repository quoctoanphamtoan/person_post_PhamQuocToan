package com.quoctoanphamtoan.person_post.repository;

import com.quoctoanphamtoan.person_post.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u where u.userName = ?1")
    User findUserByUserName(String name);
}
