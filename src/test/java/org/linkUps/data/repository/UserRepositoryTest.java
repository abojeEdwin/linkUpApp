package org.linkUps.data.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.linkUps.data.models.Gender;
import org.linkUps.data.models.User;
import org.linkUps.data.models.Weight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void CleanBefore() {
        userRepository.deleteAll();
    }

    @AfterEach
    void CleanAfter() {
        userRepository.deleteAll();
    }

    @Test
    public void testToSaveAUserIntoTheRepository() {
        User user = new User();
        user.setUsername("Edwin");
        user.setEmail("abojeedwin@gmail.com.com");
        user.setPassword("password");
        userRepository.save(user);
        assertEquals(1, userRepository.count());
    }
    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("Edwin");
        user.setEmail("abojeedwin@gmail.com.com");
        user.setPassword("password");
        userRepository.save(user);
        User found = userRepository.findByUsername(user.getUsername());
        assertEquals("Edwin", found.getUsername());
    }

    @Test
    public void testFindUserById(){
        User user = new User();
        user.setEmail("abojeedwin@gmail.com");
        user.setPassword("password");
        user.setWeight(Weight.EXTRA_LARGE);
        User savedUser = userRepository.save(user);
        List<String> userId = new ArrayList<>();
        userId.add(savedUser.getId());
        List <User> foundUserId = userRepository.findAllById(userId);
        assertEquals(1,foundUserId.size());
    }

    @Test
    public void testToSaveUserAndDelete(){
        User user = new User();
        user.setUsername("Edwin");
        user.setPassword("password");
        user.setGender(Gender.MALE);
        User savedUser = userRepository.save(user);
        assertEquals(1, userRepository.count());
        userRepository.delete(user);
        assertEquals(0, userRepository.count());
    }

    @Test
    public void testToFindUserByEmail(){
        User user = new User();
        user.setUsername("Edwin");
        user.setPassword("password");
        user.setEmail("kilometers@gmail.com");
        user.setGender(Gender.MALE);
        User savedUser = userRepository.save(user);
        assertTrue(userRepository.existsByEmail(user.getEmail()));
    }

}