package mk.ukim.finki.wp.lab;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public DataHolder(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @PostConstruct
    public void init() {
        if (dishRepository.count() == 0) {
            Dish pasta = new Dish("D001", "Pasta Carbonara", "Italian", 20);
            Dish sushi = new Dish("D002", "Sushi Roll", "Japanese", 30);
            Dish tacos = new Dish("D003", "Tacos", "Mexican", 15);

            Chef gordon = new Chef("Gordon", "Ramsay", "Famous chef", List.of(pasta, tacos));
            Chef jamie = new Chef("Jamie", "Oliver", "Well known chef", List.of(sushi));

            chefRepository.save(gordon);
            chefRepository.save(jamie);
        }
    }

}

