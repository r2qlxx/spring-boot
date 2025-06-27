package com.example.demo.application.controller;

import com.example.demo.domain.object.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/demo")
public class MvcController {

    // Match to
    // localhost/demo
    // localhost/demo/
    @GetMapping({"", "/"})
    public String index(Model model) {
        User user = User.builder().userid(1).username("mvc-user").build();
        List fruits = new ArrayList<>(Arrays.asList("orange", "apple", "cherry"));
        model.addAttribute("message", "Hello");
        model.addAttribute("user", user);
        model.addAttribute("fruits", fruits);
        return "index";
    }
}
