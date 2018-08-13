package com.example.bookmanagementsystem.entity.user;

import com.example.bookmanagementsystem.entity.BasicEntity;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String country;

    @Column
    private String province;

    @Column
    private String city;

    @Column
    private String district;

    @Column
    private String details;

    @Column
    private Boolean isMunicipality;

    @Column
    private Boolean isDelete;

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

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Address() {
    }

    public Address(String country, String province, String city, String district, String details, Boolean isMunicipality, Boolean isDelete) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.district = district;
        this.details = details;
        this.isMunicipality = isMunicipality;
        this.isDelete = isDelete;
    }
}
