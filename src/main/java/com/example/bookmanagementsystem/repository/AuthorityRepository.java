package com.example.bookmanagementsystem.repository;

import com.example.bookmanagementsystem.entity.authentication.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: book-management-system
 * @description: 角色JPA接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findAuthorityByName(String name);

    Page<Authority> findAll(Pageable pageable);
}
