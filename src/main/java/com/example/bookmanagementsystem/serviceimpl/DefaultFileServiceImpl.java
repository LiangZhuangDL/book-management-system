package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.entity.DefaultFile;
import com.example.bookmanagementsystem.repository.DefaultFileRepository;
import com.example.bookmanagementsystem.service.DefaultFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultFileServiceImpl implements DefaultFileService {

    @Autowired
    private DefaultFileRepository defaultFileRepository;

    @Override
    public DefaultFile save(DefaultFile defaultFile) {
        return defaultFileRepository.save(defaultFile);
    }
}
