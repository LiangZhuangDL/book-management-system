package com.example.bookmanagementsystem.repository;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicUserRepository extends JpaRepository<BasicUser, Long> {
}
