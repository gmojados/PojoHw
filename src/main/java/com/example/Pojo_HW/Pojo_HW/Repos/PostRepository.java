package com.example.Pojo_HW.Pojo_HW.Repos;

import com.example.Pojo_HW.Pojo_HW.Model.Post;
import com.example.Pojo_HW.Pojo_HW.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends CrudRepository <Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.id = :postId")
    Post findPostById(@Param("postId") Long postId);

}
