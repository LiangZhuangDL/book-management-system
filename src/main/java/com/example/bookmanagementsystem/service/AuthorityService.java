package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.dto.AuthorityDTO;
import com.example.bookmanagementsystem.entity.authentication.Authority;

public interface AuthorityService {

    Boolean save(Authority authority);

    Boolean addAuthority(AuthorityDTO authorityDTO);

    Boolean removeAuthority(AuthorityDTO authorityDTO);
}
