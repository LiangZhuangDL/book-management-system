package com.example.bookmanagementsystem.repository;

import com.example.bookmanagementsystem.entity.authentication.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findAuthorityByName(String name);
}
