package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init() {
        chefs.add(new Chef(1L, "Ana", "Petrova", "Expert in Mediterranean cuisine.", new ArrayList<>()));
        chefs.add(new Chef(2L, "Marko", "Iliev", "Master of Balkan traditional dishes.", new ArrayList<>()));
        chefs.add(new Chef(3L, "Sofia", "Nikoloska", "Creative pastry chef with modern style.", new ArrayList<>()));
        chefs.add(new Chef(4L, "David", "Trajkov", "Healthy food enthusiast focused on vegan dishes.", new ArrayList<>()));
        chefs.add(new Chef(5L, "Elena", "Stojanova", "Chef with rich international culinary background.", new ArrayList<>()));

        dishes.add(new Dish("d1", "Greek Salad", "Mediterranean", 10));
        dishes.add(new Dish("d2", "Ajvar Burger", "Balkan Fusion", 25));
        dishes.add(new Dish("d3", "Chocolate Lava Cake", "Dessert", 35));
        dishes.add(new Dish("d4", "Sushi Rolls", "Japanese", 50));
        dishes.add(new Dish("d5", "Vegan Buddha Bowl", "Vegan", 20));
    }
}
