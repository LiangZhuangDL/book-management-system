package com.example.bookmanagementsystem.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @program: book-management-system
 * @description: 基础信息抽象类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BasicEntity {

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

    @LastModifiedBy
    private String updateUser;

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
