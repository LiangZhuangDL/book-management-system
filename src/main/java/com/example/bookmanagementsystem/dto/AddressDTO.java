package com.example.bookmanagementsystem.dto;

/**
 * @program: book-management-system
 * @description: 地址DTO类
 * @author: Simon Zhuang
 * @create: 2018-08-13 16:22
 **/
public class AddressDTO {

    private String country;

    private String province;

    private String city;

    private String district;

    private String details;

    private Boolean isMunicipality;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getMunicipality() {
        return isMunicipality;
    }

    public void setMunicipality(Boolean municipality) {
        isMunicipality = municipality;
    }

    public AddressDTO() {
    }

    public AddressDTO(String country, String province, String city, String district, String details, Boolean isMunicipality) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.district = district;
        this.details = details;
        this.isMunicipality = isMunicipality;
    }
}
