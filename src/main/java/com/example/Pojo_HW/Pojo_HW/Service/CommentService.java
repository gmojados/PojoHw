package com.example.Pojo_HW.Pojo_HW.Service;

import com.example.Pojo_HW.Pojo_HW.Exceptions.ResourceNotFoundException;
import com.example.Pojo_HW.Pojo_HW.Exceptions.UserNotFoundException;
import com.example.Pojo_HW.Pojo_HW.Model.Comment;
import com.example.Pojo_HW.Pojo_HW.Repos.CommentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public Comment createComment(Comment comment) {
        logger.info("Post Successfully Created");
        return commentRepository.save(comment);
    }

    public Comment getCommentById(Long commentId) {
        if (commentId == null) {
            throw new IllegalArgumentException("Post ID cannot be null");
        }

        Comment comment = commentRepository.findCommentById(commentId);
        if (comment == null) {
            throw new UserNotFoundException("Post with id " + commentId + " not found");
        }

        return comment;
    }


    public Iterable<Comment> getAllComments (){
        logger.info("Retrieved All Posts");
        Iterable<Comment> comments= commentRepository.findAll();
        return comments;
    }

    public void deleteComment(Long postId){
        if (postId == null) {
            logger.error("Post not found");
        }
        logger.info("Post has Successfully been Deleted");
        commentRepository.deleteById(postId);
    }
    @Transactional
    public Comment updateComment(Comment comment, Long commentId) {
        Comment existingComment = commentRepository.findCommentById(commentId);

        if (existingComment == null) {
            throw new ResourceNotFoundException("Comment not found with id: " + commentId);
        }

        existingComment.setContent(comment.getContent());

        return commentRepository.save(existingComment);
    }

}
