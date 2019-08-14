package ru.eltex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User("Zon", "1000"));
        model.addAttribute("msg", "Hello");
        return "index";
    }
}
