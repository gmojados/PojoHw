package com.example.Pojo_HW.Pojo_HW.Controller;

import com.example.Pojo_HW.Pojo_HW.Model.Post;

import com.example.Pojo_HW.Pojo_HW.Model.User;
import com.example.Pojo_HW.Pojo_HW.Response.PostResponse;
import com.example.Pojo_HW.Pojo_HW.Service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    @Autowired
    private PostResponse postResponse;

    @PostMapping(value = "/post/{userID}")
    public ResponseEntity<?> createPost (@Validated @RequestBody Post post){
        return new ResponseEntity<>(postResponse.createPost(post), HttpStatus.CREATED);

    }
    @GetMapping(value = "/post/{userId}")
    public ResponseEntity<?> getPostByUserId (@PathVariable Long userId){
        postResponse.getPostsByUserId(userId);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping(value = "/posts")
    public ResponseEntity<?> getAllPosts (){
        postResponse.getAllPosts();
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @DeleteMapping(value = "/post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        postResponse.deletePost(postId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(value = "/post/{postId}")
    public ResponseEntity<?> updatePost (@RequestBody Post post, @PathVariable Long postId){
        postResponse.updatePost(postId, post);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}

