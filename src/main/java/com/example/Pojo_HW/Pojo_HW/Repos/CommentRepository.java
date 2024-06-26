package com.example.Pojo_HW.Pojo_HW.Repos;

import com.example.Pojo_HW.Pojo_HW.Model.Comment;
import com.example.Pojo_HW.Pojo_HW.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.id = :commentId")
    Comment findCommentById(@Param("commentId") Long commentId);
}


