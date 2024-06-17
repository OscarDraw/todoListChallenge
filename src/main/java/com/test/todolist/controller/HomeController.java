package com.test.todolist.controller;

import com.test.todolist.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String index(Model model, HttpSession session){
        String username = (String) session.getAttribute("loggedInUser");

        if (username != null) {
            model.addAttribute("username", username);
            model.addAttribute("isLoggedIn", true);
        } else {
            model.addAttribute("isLoggedIn", false);
        }

        model.addAttribute("task", taskService.getAll());
        return "index";
    }
}
