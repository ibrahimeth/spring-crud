package com.springdemo.springdemo.repository;

import com.springdemo.springdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
