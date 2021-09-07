package com.hillesheim.springbootmongodb.resources;

import java.util.Date;
import java.util.List;

import com.hillesheim.springbootmongodb.domain.Post;
import com.hillesheim.springbootmongodb.resources.util.URL;
import com.hillesheim.springbootmongodb.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> posts = service.findByTitle(text);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDateText,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDateText) {
        text = URL.decodeParam(text);
        Date minDate = URL.convertDate(minDateText, new Date(0L));
        Date maxDate = URL.convertDate(maxDateText, new Date());
        List<Post> posts = service.fullSearch(text, minDate, maxDate);
        return ResponseEntity.ok().body(posts);
    }
}
