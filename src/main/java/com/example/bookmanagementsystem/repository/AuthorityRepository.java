package com.example.bookmanagementsystem.repository;

import com.example.bookmanagementsystem.entity.authentication.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findAuthorityByName(String name);

    Page<Authority> findAll(Pageable pageable);
}
