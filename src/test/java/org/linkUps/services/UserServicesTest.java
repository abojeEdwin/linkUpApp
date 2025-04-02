package org.linkUps.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.linkUps.data.models.*;
import org.linkUps.data.repository.UserRepository;
import org.linkUps.exceptions.DuplicateEmailException;
import org.linkUps.exceptions.InvalidPasswordException;
import org.linkUps.exceptions.NoUserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServicesTest {


    @Autowired
    private UserServices userService;
    private Profile profile;
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        profile = new Profile();
        userService.deleteAll();
    }

    @AfterEach
    void tearDown() {
        userService.deleteAll();
    }

    @Test
    public void testThatUserCanRegister(){
        User user = new User();
        user.setUsername("choko");
        user.setGender(Gender.MALE);
        user.setWeight(Weight.MEDIUM);
        user.setPassword("password");
        user.setEmail("abojeedwin@gmail.com");
        profile.setLocation("Abuja");
        userService.register(user,profile);
        assertNotNull(userService);
        assertEquals(1,userService.count());
    }

    @Test
    public void testForDuplicateUser(){
        User user = new User();
        user.setUsername("salamanda");
        user.setGender(Gender.MALE);
        user.setWeight(Weight.MEDIUM);
        user.setEmail("salah@gmail.com");
        profile.setLocation("Abuja");
        profile.setHeight(Height.TALL);
        userService.register(user,profile);

        User user1 = new User();
        user1.setUsername("salamanda");
        user1.setGender(Gender.MALE);
        user1.setWeight(Weight.MEDIUM);
        user1.setEmail("salah@gmail.com");
        assertThrows(DuplicateEmailException.class,()->userService.register(user1,profile));
        assertEquals(1,userService.count());

    }

    @Test
    public void testWhenUserExistsInTheSystem() {
        User user = new User();
        user.setEmail("jakens@gmail.com");
        user.setWeight(Weight.EXTRA_LARGE);
        user.setUsername("Madmike");
        user.setPassword("Writeme");
        profile.setLocation("Abuja");
        userService.register(user,profile);
        assertThrows(NoUserFoundException.class,()->userService.login(" abojeedwin@gmail.com","password"));
    }

    @Test
    public void testWhenUserLogsIn(){
        User user = new User();
        user.setUsername("mario");
        user.setEmail("mariolee@gmail.com");
        user.setPassword("rightPassword");
        user.setWeight(Weight.EXTRA_LARGE);
        userService.register(user,profile);
        assertThrows(InvalidPasswordException.class,()-> userService.login("mariolee@gmail.com","mypassword"));
    }

    @Test
    public void testToDeleteUserById(){
        User user = new User();
        user.setUsername("mario");
        user.setPassword("password");
        user.setWeight(Weight.LARGE);
        userService.register(user,profile);
        assertEquals(1,userService.count());
        userService.deleteUserById(user.getId());
        assertEquals(0,userService.count());
    }

    @Test
    public void testToFindUserById(){
        User user = new User();
        user.setUsername("mario");
        user.setPassword("password");
        user.setWeight(Weight.EXTRA_EXTRA_LARGE);
        List<String> userId = new ArrayList<>();
        userId.add(user.getId());
        userService.register(user,profile);
        User found = userService.findById(user.getId());
        assertEquals(1,userId.size());
    }

}