package com.wavetest.test.controller;

import com.wavetest.test.entity.User;
import com.wavetest.test.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return adminService.getUser(id);
    }

    @PostMapping("/user")
    public User addNewUser(@RequestBody User user) {
        return adminService.addNewUser(user);
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        return adminService.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
    }

}
