package com.example.demoparkapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoparkapi.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

	
	@Query("SELECT u.role FROM User u WHERE u.username LIKE :username")
	String findRoleByName(@Param("username") String username);

	
}
