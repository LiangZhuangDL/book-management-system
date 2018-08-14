package com.example.bookmanagementsystem.entity.user;

import com.example.bookmanagementsystem.entity.BasicEntity;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import javax.persistence.*;
import java.util.Date;

/**
 * @program: book-management-system
 * @description: 用户详情实体类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@Entity
@Table(name = "user_details")
public class UserDetails extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String avatar;

    @Column
    private String realName;

    @Column
    private Boolean sex;

    @Column
    private String idCardNumber;

    @Column
    private String telephone;

    @Column
    private String cellphone;

    @Column
    private Date birthday;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_details_address", joinColumns = @JoinColumn(name = "user_details_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"))
    private Address address;

    @Column
    private String zipCode;

    @Column
    private Boolean isDelete = false;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_details_basic_user", joinColumns = @JoinColumn(name = "user_details_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "basic_user_id", referencedColumnName = "id"))
    private BasicUser basicUser;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public BasicUser getBasicUser() {
        return basicUser;
    }

    public void setBasicUser(BasicUser basicUser) {
        this.basicUser = basicUser;
    }

    public UserDetails() {
    }

    public UserDetails(String avatar, String realName, Boolean sex, String idCardNumber, String telephone, String cellphone, Date birthday, Address address, String zipCode, Boolean isDelete, BasicUser basicUser) {
        this.avatar = avatar;
        this.realName = realName;
        this.sex = sex;
        this.idCardNumber = idCardNumber;
        this.telephone = telephone;
        this.cellphone = cellphone;
        this.birthday = birthday;
        this.address = address;
        this.zipCode = zipCode;
        this.isDelete = isDelete;
        this.basicUser = basicUser;
    }
}
