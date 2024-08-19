package com.decoder.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.decoder.demo.model.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {

	Users findByUsername(String username);
}
