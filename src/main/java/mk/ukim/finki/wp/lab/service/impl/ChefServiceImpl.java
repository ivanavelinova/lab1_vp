package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listAll() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElse(null);
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef = chefRepository.findById(chefId).orElse(null);

        if (chef == null) {
            return null;
        }

        try {
            Long dishIdLong = Long.parseLong(dishId);
            Dish dish = dishRepository.findById(dishIdLong).orElse(null);

            if (dish == null) {
                return null;
            }

            // Поврзување на јадење со готвач
            dish.setChef(chef);
            chef.getDishes().add(dish);

            dishRepository.save(dish);   // Save јадење
            chefRepository.save(chef);   // Save готвач

            return chef;
        } catch (NumberFormatException e) {
            return null;
        }
    }



}
