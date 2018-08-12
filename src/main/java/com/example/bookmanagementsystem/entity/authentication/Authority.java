package com.example.bookmanagementsystem.entity.authentication;

import com.example.bookmanagementsystem.entity.BasicEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority extends BasicEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private Boolean isDelete = false;

    @Override
    public String getAuthority() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Authority() {
    }

    public Authority(String name) {
        this.name = name;
    }


}
