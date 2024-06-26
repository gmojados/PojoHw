package com.example.Pojo_HW.Pojo_HW.Response;

import com.example.Pojo_HW.Pojo_HW.Exceptions.ResourceNotFoundException;
import com.example.Pojo_HW.Pojo_HW.Exceptions.UserNotFoundException;
import com.example.Pojo_HW.Pojo_HW.Model.User;
import com.example.Pojo_HW.Pojo_HW.Service.UserService;
import com.example.Pojo_HW.Pojo_HW.dto.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class UserResponse {
    @Autowired
    UserService userService;

    public ResponseEntity<?> createUser(User user) {
        try {
            User createdUser = userService.createUser(user);

            Body body = new Body();
            body.setData(createdUser);
            body.setCode(HttpStatus.CREATED.value());
            body.setMessage("User created");

            return ResponseEntity.ok(body);
        } catch (Exception exception) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("error fetching creating User");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<?> getAllUsers() {
        try {
            Body body = new Body();
            body.setData(userService.getUsers());
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Success");

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception exception) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("Error fetching users");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getUserById(Long userId) {
        Body body = new Body();
        try {
            User user = userService.getUserById(userId);

            body.setData(user);
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Account retrieval success");

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("Error fetching account");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error fetching account");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> updateUser(long id, User user) {
        try {
            User editedUser = userService.updateUser(user, id);

            if (editedUser != null) {
                Body body = new Body();
                body.setData(editedUser);
                body.setCode(HttpStatus.OK.value());
                body.setMessage("User updated successfully");

                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                Body body = new Body();
                body.setCode(HttpStatus.NOT_FOUND.value());
                body.setMessage("Usernot found");

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
            body.setMessage("Error updating User");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteUser(Long userId) {
        try {
            userService.deleteUser(userId);
            Body body = new Body();
            body.setCode(HttpStatus.NO_CONTENT.value());
            body.setMessage("User deleted successfully");

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