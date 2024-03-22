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
     user.setEmail("qwer.123@gmail.com");
     user.setPassword("qwert");
     user.setFirstName("Kim");
     user.setLastName("Lan");

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
        Integer userID = 1;
        Optional<User> optionalUser = repo.findById(userID);
        User user = optionalUser.get();
        user.setPassword("abc12345");
        repo.save(user);
        User updatUser = repo.findById(userID).get();
        Assertions.assertThat(updatUser.getPassword()).isEqualTo("hello2000");
    }
    @Test
    public void testGet(){
        Integer userID = 2;
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

