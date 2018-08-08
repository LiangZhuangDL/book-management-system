package com.example.bookmanagementsystem.entity;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Document
public class DefaultFile {
    @Id
    private String id;

    private String name;

    private String contentType;

    private Long size;

    private Date uploadTime;

    private Binary content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Binary getContent() {
        return content;
    }

    public void setContent(Binary content) {
        this.content = content;
    }

    public DefaultFile() {
    }

    public DefaultFile(String name, String contentType, Long size, Binary content) {
        this.name = name;
        this.contentType = contentType;
        this.uploadTime = new Date();
        this.size = size;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultFile)) return false;
        DefaultFile that = (DefaultFile) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getContentType(), that.getContentType()) &&
                Objects.equals(getSize(), that.getSize()) &&
                Objects.equals(getUploadTime(), that.getUploadTime()) &&
                Objects.equals(getContent(), that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getContentType(), getSize(), getUploadTime(), getContent());
    }
}
