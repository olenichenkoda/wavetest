package com.wavetest.test.service;

import com.wavetest.test.entity.User;
import com.wavetest.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    @Transactional
    public List<User> getAllUsers() {
       return userRepository.findAll().stream().collect(Collectors.toList());
    }

    @Transactional
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
