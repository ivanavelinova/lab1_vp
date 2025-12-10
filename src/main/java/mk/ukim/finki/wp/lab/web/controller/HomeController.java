package mk.ukim.finki.wp.lab.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Пренасочува на листата на јадења
        return "redirect:/dishes";
    }
}
