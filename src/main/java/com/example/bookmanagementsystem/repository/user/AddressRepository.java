package com.example.bookmanagementsystem.repository.user;

import com.example.bookmanagementsystem.entity.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: book-management-system
 * @description: 地址JPA接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface AddressRepository extends JpaRepository<Address, Long> {
}
