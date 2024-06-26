package com.example.Pojo_HW.Pojo_HW.Repos;

import com.example.Pojo_HW.Pojo_HW.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.id = :userId")
    User findByID(@Param("userId") Long userId);

    List<User> findAll();
}
