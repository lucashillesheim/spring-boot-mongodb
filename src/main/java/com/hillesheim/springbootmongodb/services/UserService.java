package com.hillesheim.springbootmongodb.services;

import java.util.List;
import java.util.Optional;

import com.hillesheim.springbootmongodb.domain.User;
import com.hillesheim.springbootmongodb.repository.UserRepository;
import com.hillesheim.springbootmongodb.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }
}
