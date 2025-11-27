package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public String getDishesPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("dishes", dishService.listDishes());
        model.addAttribute("error", error);
        return "listDishes";
    }

    @GetMapping("/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("formAction", "/dishes/add");
        return "dish-form";
    }

    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        Dish dish = dishService.findById(id);
        if (dish == null) {
            return "redirect:/dishes?error=DishNotFound";
        }
        model.addAttribute("dish", dish);
        model.addAttribute("formAction", "/dishes/edit/" + id);
        return "dish-form";
    }

    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {
        dishService.create(dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {
        Dish updated = dishService.update(id, dishId, name, cuisine, preparationTime);
        if (updated == null) {
            return "redirect:/dishes?error=DishNotFound";
        }
        return "redirect:/dishes";
    }

    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }

}
