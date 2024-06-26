package com.example.Pojo_HW.Pojo_HW.Controller;

import com.example.Pojo_HW.Pojo_HW.Model.Comment;
import com.example.Pojo_HW.Pojo_HW.Model.Post;
import com.example.Pojo_HW.Pojo_HW.Response.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentResponse commentResponse;
    @PostMapping("users/{postId}/comments")
    public ResponseEntity<?> createComment(@RequestBody Comment comment, @PathVariable long postId){
        return commentResponse.createComment(comment,postId);
    }

    @GetMapping("/comments")
    public ResponseEntity<?> getAllComments() {
        return new ResponseEntity<>(commentResponse.getAllComments(), HttpStatus.OK);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<?> getCommentById(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentResponse.getCommentsById(commentId), HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<?> getCommentsByPost(@PathVariable long postId) {
        return commentResponse.getCommentsById(postId);
    }


    @PutMapping("/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable int commentId, @RequestBody Comment comment){
        return commentResponse.updateComment(commentId,comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") long commentId){
        return commentResponse.deleteComment(commentId);
    }

}




