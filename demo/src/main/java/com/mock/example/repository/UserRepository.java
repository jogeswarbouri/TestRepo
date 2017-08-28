package com.mock.example.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.mock.example.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

  public User findByUsername(String username);

}
