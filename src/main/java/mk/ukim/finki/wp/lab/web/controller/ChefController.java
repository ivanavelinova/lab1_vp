package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chefs")
public class ChefController {

    private final ChefService chefService;
    private final DishService dishService;

    public ChefController(ChefService chefService, DishService dishService) {
        this.chefService = chefService;
        this.dishService = dishService;
    }

    // Листање на сите готвачи
    @GetMapping
    public String getChefsPage(Model model) {
        List<Chef> chefs = chefService.listChefs();
        model.addAttribute("chefs", chefs);
        model.addAttribute("dishes", dishService.listDishes()); // ⚡ додадено
        return "chefs-list";
    }

    // Детали за готвач
    @GetMapping("/{id}")
    public String getChefDetails(@PathVariable Long id, Model model) {
        Chef chef = chefService.findById(id);
        if (chef == null) {
            return "redirect:/chefs?error=ChefNotFound";
        }
        model.addAttribute("chefName", chef.getFirstName() + " " + chef.getLastName());
        model.addAttribute("chefBio", chef.getBio());
        model.addAttribute("dishes", chef.getDishes());
        model.addAttribute("chefId", chef.getId());
        return "chef-details";
    }

    // Страница за избор на јадење за додавање кај готвач
    @GetMapping("/{id}/add-dish")
    public String getAddDishToChefForm(@PathVariable Long id, Model model) {
        Chef chef = chefService.findById(id);
        if (chef == null) {
            return "redirect:/chefs?error=ChefNotFound";
        }
        model.addAttribute("chefId", chef.getId());
        model.addAttribute("chefName", chef.getFirstName() + " " + chef.getLastName());
        model.addAttribute("dishes", dishService.listDishes());
        return "add-dish";
    }

    // Додавање јадење кај готвач
    @PostMapping("/{chefId}/add-dish")
    public String addDishToChef(@PathVariable Long chefId,
                                @RequestParam String dishId) {
        chefService.addDishToChef(chefId, dishId);
        return "redirect:/chefs/" + chefId;
    }
}
