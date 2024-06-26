package com.example.Pojo_HW.Pojo_HW.Response;

import com.example.Pojo_HW.Pojo_HW.Exceptions.ResourceNotFoundException;
import com.example.Pojo_HW.Pojo_HW.Exceptions.UserNotFoundException;
import com.example.Pojo_HW.Pojo_HW.Model.Comment;
import com.example.Pojo_HW.Pojo_HW.Service.CommentService;
import com.example.Pojo_HW.Pojo_HW.dto.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentResponse {
    @Autowired
    CommentService commentService;


    public ResponseEntity<?> createComment(Comment comment, long postId) {
        try {
            Comment createdComment = commentService.createComment(comment);

            Body body = new Body();
            body.setData(createdComment);
            body.setCode(HttpStatus.CREATED.value());
            body.setMessage("Comment entered");

            return ResponseEntity.ok(body);
        } catch (Exception exception) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("error creating comment");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<?> getAllComments() {
        try {
            Body body = new Body();
            body.setData(commentService.getAllComments());
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Success");

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception exception) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("Error fetching Comments");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getCommentsById(Long commentId) {
        Body body = new Body();
        try {
            Comment comment = commentService.getCommentById(commentId);

            body.setData(comment);
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Comment Found");

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("Error fetching Comment");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error fetching post");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> updateComment(long id, Comment comment) {
        try {
            Comment editedComment = commentService.updateComment(comment, id);

            if (editedComment != null) {
                Body body = new Body();
                body.setData(editedComment);
                body.setCode(HttpStatus.OK.value());
                body.setMessage("Comment updated successfully");

                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                Body body = new Body();
                body.setCode(HttpStatus.NOT_FOUND.value());
                body.setMessage("Comment Not found");

                return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
            }
        } catch (ResourceNotFoundException e) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage(e.getMessage());

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error updating Comment");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteComment(Long userId) {
        try {
            commentService.deleteComment(userId);
            Body body = new Body();
            body.setCode(HttpStatus.NO_CONTENT.value());
            body.setMessage("Post deleted successfully");

            return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage(e.getMessage());

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }
}



