package com.tavant.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tavant.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select c from User c where c.username = :username and c.password = :password")
	public User findByUserDetails(@Param("username") String username, @Param("password") String password);

}
