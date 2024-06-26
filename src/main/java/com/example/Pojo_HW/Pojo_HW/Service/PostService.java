package com.example.Pojo_HW.Pojo_HW.Service;

import com.example.Pojo_HW.Pojo_HW.Exceptions.ResourceNotFoundException;
import com.example.Pojo_HW.Pojo_HW.Exceptions.UserNotFoundException;
import com.example.Pojo_HW.Pojo_HW.Model.Post;
import com.example.Pojo_HW.Pojo_HW.Model.User;
import com.example.Pojo_HW.Pojo_HW.Repos.PostRepository;
import com.example.Pojo_HW.Pojo_HW.Repos.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
@Service

public class PostService {
    @Autowired
    PostRepository postRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public Post createPost(Post post) {
        logger.info("Post Successfully Created");
        return postRepository.save(post);
    }

    public Post getPostById(Long postId) {
        if (postId == null) {
            throw new IllegalArgumentException("Post ID cannot be null");
        }

        Post post = postRepository.findPostById(postId);
        if (post == null) {
            throw new UserNotFoundException("Post with id " + postId + " not found");
        }

        return post;
    }


    public Iterable<Post> getAllPosts (){
        logger.info("Retrieved All Posts");
        Iterable<Post> posts = postRepository.findAll();
        return posts;
    }

    public void deletePost(Long postId){
        if (postId == null) {
            logger.error("Post not found");
        }
        logger.info("Post has Successfully been Deleted");
        postRepository.deleteById(postId);
    }
    @Transactional
    public Post updatePost(Post post, Long postId) {
        Post existingPost = postRepository.findPostById(postId);

        existingPost.setContent(post.getContent());
        existingPost.setTitle(post.getTitle());

        return postRepository.save(existingPost);
    }
}
