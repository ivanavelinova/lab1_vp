package mk.ukim.finki.wp.lab.repository.impl;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryDishRepository implements DishRepository {

    private final List<Dish> dishes = new ArrayList<>();


    @PostConstruct
    public void init() {
        save(new Dish("d1", "Spaghetti", "Italian", 20));
        save(new Dish("d2", "Sushi", "Japanese", 30));
    }

    @Override
    public List<Dish> findAll() {
        return dishes;
    }

    @Override
    public Dish findByDishId(String dishId) {
        return dishes.stream()
                .filter(d -> d.getDishId().equals(dishId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return dishes.stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    @Override
    public Dish save(Dish dish) {
        Optional<Dish> existing = findById(dish.getId());
        if (existing.isPresent()) {
            Dish d = existing.get();
            d.setDishId(dish.getDishId());
            d.setName(dish.getName());
            d.setCuisine(dish.getCuisine());
            d.setPreparationTime(dish.getPreparationTime());
            return d;
        } else {
            dishes.add(dish);
            return dish;
        }
    }

    @Override
    public void deleteById(Long id) {
        dishes.removeIf(d -> d.getId().equals(id));
    }
}
