package com.example.Pojo_HW.Pojo_HW.Service;

import com.example.Pojo_HW.Pojo_HW.Exceptions.UserNotFoundException;
import com.example.Pojo_HW.Pojo_HW.Model.User;
import com.example.Pojo_HW.Pojo_HW.Repos.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class UserService {
        @Autowired
        UserRepository userRepository;
        private final Logger logger = LoggerFactory.getLogger(UserService.class);

        public User createUser(User user) {
            logger.info("User Successfully Created");
            return userRepository.save(user);
        }

    public User getUserById(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        User user = userRepository.findByID(userId);
        if (user == null) {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }

        return user;
    }


        public List<User> getUsers (){
            logger.info("Retrieved All Users");
            List<User> users = userRepository.findAll();
            return users;
        }

        public void deleteUser(Long userId){
            if (userId == null) {
                logger.error("User not found");
            }
            logger.info("User has Successfully been Deleted");
            userRepository.deleteById(userId);
        }
        @Transactional
        public User updateUser (User user, Long userId){
            User existingUser = userRepository.findByID(userId);
            existingUser.setGender(user.getGender());
            existingUser.setName(user.getName());
            return userRepository.save(existingUser);
        }

    }
