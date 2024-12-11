package com.springdemo.springdemo.controller;

import com.springdemo.springdemo.model.User;
import com.springdemo.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/users
/*
    CREATE DATABASE my_database;
    USE my_database;
    CREATE TABLE user (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100),
        authority varchar(100),
        email VARCHAR(100),
        phone VARCHAR(15)
    );
*/
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update-form"; // Form düzenleme sayfası
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        if (user.getId() != null && userService.getUserRepository().existsById(user.getId())) {
            userService.saveUser(user); // Güncelleme işlemi
            System.out.println("güncelle");
        } else {
            System.out.println("güncelleme");
            System.out.println("user Id => " + user.getId() + " peki kayıtlı mı ? => " );

            // ID boşsa hata döndür veya yeni kayıt ekle
        }
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}

