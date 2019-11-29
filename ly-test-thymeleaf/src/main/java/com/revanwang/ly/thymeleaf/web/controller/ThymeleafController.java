package com.revanwang.ly.thymeleaf.web.controller;


import com.revanwang.ly.thymeleaf.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThymeleafController {

    @GetMapping("show")
    public String show(Model model) {

        model.addAttribute("msg", "Hello, Thymeleaf");

        return "item";
    }


    @GetMapping("show2")
    public String show2(Model model) {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setAge(21);
        user1.setName("Revan1");
        user1.setImg("http://image.leyou.com/images/10/7/1524297598540.jpg");
        users.add(user1);

        User user2 = new User();
        user2.setAge(22);
        user2.setName("Revan2");
        user2.setImg("http://image.leyou.com/images/15/5/1524297599029.jpg");
        users.add(user2);

        User user3 = new User();
        user3.setAge(23);
        user3.setName("Revan3");
        user3.setImg("http://image.leyou.com/images/12/13/1524297599389.jpg");
        users.add(user3);

        model.addAttribute("users", users);

        return "item";
    }

}
