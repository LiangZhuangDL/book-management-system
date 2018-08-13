package com.example.bookmanagementsystem.dto;

import java.util.Date;

/**
 * @program: book-management-system
 * @description: 用户详细信息DTO类
 * @author: Simon Zhuang
 * @create: 2018-08-13 16:12
 **/
public class UserDetailsDTO {

    private String realName;

    private Boolean sex;

    private String idCardNumber;

    private String telephone;

    private String cellphone;

    private Date birthday;

    private String zipCode;

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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(String realName, Boolean sex, String idCardNumber, String telephone, String cellphone, Date birthday, String zipCode) {
        this.realName = realName;
        this.sex = sex;
        this.idCardNumber = idCardNumber;
        this.telephone = telephone;
        this.cellphone = cellphone;
        this.birthday = birthday;
        this.zipCode = zipCode;
    }
}
