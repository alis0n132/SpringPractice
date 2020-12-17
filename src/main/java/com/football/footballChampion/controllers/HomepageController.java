package com.football.footballChampion.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {
    @GetMapping("/")
    public String startpage(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homepage(){
        return "home";
    }
}
