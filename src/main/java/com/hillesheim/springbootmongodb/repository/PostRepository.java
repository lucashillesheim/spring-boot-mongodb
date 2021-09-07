package com.hillesheim.springbootmongodb.repository;

import java.util.List;

import com.hillesheim.springbootmongodb.domain.Post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")

    List<Post> findByTitle(String text);

    List<Post> findByTitleContainingIgnoreCase(String text);
}
