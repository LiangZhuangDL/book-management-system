package com.example.bookmanagementsystem.repository;

import com.example.bookmanagementsystem.entity.DefaultFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DefaultFileRepository extends MongoRepository<DefaultFile, String> {
}
