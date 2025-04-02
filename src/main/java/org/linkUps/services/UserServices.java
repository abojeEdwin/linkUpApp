package org.linkUps.services;

import org.linkUps.exceptions.DuplicateEmailException;
import org.linkUps.data.models.Profile;
import org.linkUps.data.models.User;
import org.linkUps.data.repository.ProfileRepository;
import org.linkUps.data.repository.UserRepository;
import org.linkUps.exceptions.InvalidPasswordException;
import org.linkUps.exceptions.NoUserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public void deleteAll(){
        userRepository.deleteAll();
    }

    public User register(User user, Profile profile) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateEmailException("User already exists");
        }
        Profile savedProfile = profileRepository.save(profile);
        user.setId(savedProfile.getId());
        return userRepository.save(user);
    }

    public long count() {
        return userRepository.count();
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        throwsNoUserFoundException(user);
        throwsInvalidPasswordException(user,password);
        return user;
    }

    private void throwsInvalidPasswordException(User user, String password) {
        if(!user.getPassword().equals(password) ) {
            throw new InvalidPasswordException("Invalid password");
        }
    }

    private void throwsNoUserFoundException(User user) {
        if(user == null ){
            throw new NoUserFoundException("Invalid Email or Password");
        }
    }

    public void deleteUserById(String id) {
        userRepository.deleteAllById(Collections.singleton(id));
    }

    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
