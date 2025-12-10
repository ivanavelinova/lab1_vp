package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chefs")
public class ChefController {

    private final ChefService chefService;
    private final DishService dishService;

    public ChefController(ChefService chefService, DishService dishService) {
        this.chefService = chefService;
        this.dishService = dishService;
    }

    @GetMapping
    public String listChefs(Model model) {
        model.addAttribute("chefs", chefService.listAll());
        // *** ДОДАДЕНО: Мора да ги додадеш сите јадења во моделот! ***
        model.addAttribute("dishes", dishService.listDishes());
        return "chefs-list"; // thymeleaf template
    }

    @GetMapping("/{id}/add-dish")
    public String showAddDishForm(@PathVariable Long id, Model model) {
        model.addAttribute("chef", chefService.findById(id));
        model.addAttribute("dishes", dishService.listDishes());
        return "add-dish"; // thymeleaf template
    }

    @PostMapping("/{id}/add-dish")
    public String addDishToChef(@PathVariable Long id, @RequestParam String dishId) {
        chefService.addDishToChef(id, dishId);
        return "redirect:/chefs";
    }
    @GetMapping("/{id}")
    public String viewChefDetails(@PathVariable Long id, Model model) {
        Chef chef = chefService.findById(id);
        if (chef == null) {
            return "redirect:/chefs?error=ChefNotFound";
        }
        model.addAttribute("chef", chef);
        return "chef-details"; // Твојата Thymeleaf страница за детали на готвач
    }

}
