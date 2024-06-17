package com.test.todolist.controller;

import com.test.todolist.persistence.entity.User;
import com.test.todolist.service.TaskService;
import com.test.todolist.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model, HttpSession session){
        String username = (String) session.getAttribute("loggedInUser");

        if (username != null) {
            model.addAttribute("username", username);
            Optional<User> userOpt = userService.findByUsername(username);
            if(userOpt.isPresent()){
                model.addAttribute("userid", userOpt.get().getId());
            }
            model.addAttribute("isLoggedIn", true);
        } else {
            model.addAttribute("isLoggedIn", false);
        }

        return "index";
    }
}
