package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.dto.AuthorityDTO;
import com.example.bookmanagementsystem.entity.authentication.Authority;
import org.springframework.data.domain.Page;

public interface AuthorityService {

    Boolean save(Authority authority);

    Boolean addAuthority(AuthorityDTO authorityDTO);

    Boolean removeAuthority(AuthorityDTO authorityDTO);

    Boolean createAuthority(String authorityName);

    Boolean removeAuthority(String authorityName);

    Page<Authority> getAllAuthorities(Integer page, Integer size);
}
