package com.example.Pojo_HW.Pojo_HW.Response;

import com.example.Pojo_HW.Pojo_HW.Exceptions.ResourceNotFoundException;
import com.example.Pojo_HW.Pojo_HW.Exceptions.UserNotFoundException;
import com.example.Pojo_HW.Pojo_HW.Model.Post;
import com.example.Pojo_HW.Pojo_HW.Model.User;
import com.example.Pojo_HW.Pojo_HW.Service.PostService;
import com.example.Pojo_HW.Pojo_HW.dto.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PostResponse {
    @Autowired
    PostService postService;
    public ResponseEntity<?> createPost(Post post) {
        try {
            Post createdPost= postService.createPost(post);

            Body body = new Body();
            body.setData(createdPost);
            body.setCode(HttpStatus.CREATED.value());
            body.setMessage("Post created");

            return ResponseEntity.ok(body);
        } catch (Exception exception) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("error fetching creating Post");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<?> getAllPosts() {
        try {
            Body body = new Body();
            body.setData(postService.getAllPosts());
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Success");

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception exception) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("Error fetching Posts");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getPostsByUserId(Long userId) {
        Body body = new Body();
        try {
            Post post = postService.getPostById(userId);

            body.setData(post);
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Post retrieval success");

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("Error fetching post");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error fetching post");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> updatePost(long id, Post post) {
        try {
            Post editedPost = postService.updatePost(post, id);

            if (editedPost != null) {
                Body body = new Body();
                body.setData(editedPost);
                body.setCode(HttpStatus.OK.value());
                body.setMessage("Post updated successfully");

                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                Body body = new Body();
                body.setCode(HttpStatus.NOT_FOUND.value());
                body.setMessage("Post Not found");

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
            body.setMessage("Error updating Post");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deletePost(Long userId) {
        try {
            postService.deletePost(userId);
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
