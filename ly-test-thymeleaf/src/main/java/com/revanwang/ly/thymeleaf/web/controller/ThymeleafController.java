package com.revanwang.ly.thymeleaf.web.controller;


import com.revanwang.ly.thymeleaf.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping("show")
    public String show(Model model) {

        model.addAttribute("msg", "Hello, Thymeleaf");

        return "users";
    }


    @GetMapping("show2")
    public String show2(Model model) {

        User user = new User();
        user.setAge(21);
        user.setName("Revan");
        user.setFriend(new User("WRW", 21, null));

        model.addAttribute("user", user);

        return "users";
    }

}
