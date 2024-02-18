package com.app.repositury;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Long> {
	
	Optional<MyUser>findByEmail(String email);
}
