package com.hillesheim.springbootmongodb.services;

import java.util.List;

import com.hillesheim.springbootmongodb.domain.User;
import com.hillesheim.springbootmongodb.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }
}
