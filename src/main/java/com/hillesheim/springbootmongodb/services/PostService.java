package com.hillesheim.springbootmongodb.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.hillesheim.springbootmongodb.domain.Post;
import com.hillesheim.springbootmongodb.repository.PostRepository;
import com.hillesheim.springbootmongodb.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public List<Post> findByTitle(String text) {
        return repository.findByTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000 - 1);
        return repository.fullSearch(text, minDate, maxDate);
    }
}
