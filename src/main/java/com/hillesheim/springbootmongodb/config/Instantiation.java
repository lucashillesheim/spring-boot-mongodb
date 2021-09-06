package com.hillesheim.springbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import com.hillesheim.springbootmongodb.domain.Post;
import com.hillesheim.springbootmongodb.domain.User;
import com.hillesheim.springbootmongodb.dto.AuthorDTO;
import com.hillesheim.springbootmongodb.dto.CommentDTO;
import com.hillesheim.springbootmongodb.repository.PostRepository;
import com.hillesheim.springbootmongodb.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userReposiroty;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... arg0) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        userReposiroty.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userReposiroty.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, dateFormat.parse("03/21/2021"), "Partiu viagem",
                "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, dateFormat.parse("03/23/2021"), "Bom dia", "Acordei felix hoje!",
                new AuthorDTO(maria));

        CommentDTO comment1 = new CommentDTO("Boa viagem mano!", dateFormat.parse("03/21/2021"), new AuthorDTO(alex));
        CommentDTO comment2 = new CommentDTO("Aproveite!", dateFormat.parse("03/22/2021"), new AuthorDTO(bob));
        CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia!", dateFormat.parse("03/23/2021"),
                new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userReposiroty.save(maria);

    }

}