package com.example.Pojo_HW.Pojo_HW.Controller;

import com.example.Pojo_HW.Pojo_HW.Model.User;
import com.example.Pojo_HW.Pojo_HW.Response.UserResponse;
import com.example.Pojo_HW.Pojo_HW.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
public class UserController {
    @Autowired
    private UserResponse userResponse;

    @PostMapping(value = "/users")
    public ResponseEntity<?> createUser (@Validated @RequestBody User user){
        return new ResponseEntity<>(userResponse.createUser(user), HttpStatus.CREATED);

    }
    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<?> getUserById (@PathVariable Long userId){
        userResponse.getUserById(userId);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping(value = "/users")
    public ResponseEntity<?> getAllUsers (){
        userResponse.getAllUsers();
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userResponse.deleteUser(userId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(value = "/user/{userId}")
    public ResponseEntity<?> updateUser (@RequestBody User user, @PathVariable Long userID){
        userResponse.updateUser(userID, user);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
