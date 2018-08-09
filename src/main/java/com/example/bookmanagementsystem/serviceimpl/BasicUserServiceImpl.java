package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.repository.BasicUserRepository;
import com.example.bookmanagementsystem.service.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicUserServiceImpl implements BasicUserService {

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Override
    public Boolean removeUserById(Long id) {
        try{
            basicUserRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
