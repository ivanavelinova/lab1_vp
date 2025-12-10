package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public DishServiceImpl(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return dishRepository.findByDishId(dishId);
    }

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id).orElse(null);
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime, Long chefId) {
        Dish dish = new Dish(dishId, name, cuisine, preparationTime);
        if (chefId != null) {
            Chef chef = chefRepository.findById(chefId).orElse(null);
            if (chef != null) {
                dish.setChef(chef);
                chef.getDishes().add(dish);
            }
        }
        return dishRepository.save(dish);
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime, Long chefId) {
        Dish dish = dishRepository.findById(id).orElse(null);
        if (dish == null) return null;

        dish.setDishId(dishId);
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);

        if (chefId != null) {
            Chef chef = chefRepository.findById(chefId).orElse(null);
            if (chef != null) {
                dish.setChef(chef);
                if (!chef.getDishes().contains(dish)) {
                    chef.getDishes().add(dish);
                }
            }
        }

        return dishRepository.save(dish);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}
