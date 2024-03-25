package com.myproject;


import com.myproject.user.User;
import com.myproject.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class UserRepositoryTests {
    @Autowired private UserRepository repo;
    @Test
    public void testAddNew(){
     User user = new User();
     user.setEmail("tom.holland@gmail.com");
     user.setPassword("tom98765");
     user.setFirstName("Tom");
     user.setLastName("Holland");

     User savedUser = repo.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testUpdate(){
        Integer userID = 5;
        Optional<User> optionalUser = repo.findById(userID);
        User user = optionalUser.get();
        user.setPassword("stevenson123456");
        repo.save(user);
        User updatedUser = repo.findById(userID).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("stevenson123456");
    }
    @Test
    public void testGet(){
        Integer userID = 5;
        Optional<User> optionalUser = repo.findById(userID);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());

    }
    @Test
    public void testDelete(){
        Integer userID = 2;
        repo.deleteById(userID);
        Optional<User> optionalUser = repo.findById(userID);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}

