package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;

import java.util.ArrayList;
import java.util.List;

public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    static {
        // примерни јадења
        Dish pasta = new Dish("D001", "Pasta Carbonara", "Italian", 20);
        Dish sushi = new Dish("D002", "Sushi Roll", "Japanese", 30);
        Dish tacos = new Dish("D003", "Tacos", "Mexican", 15);

        dishes.add(pasta);
        dishes.add(sushi);
        dishes.add(tacos);

        // примерни готвачи со листа на јадења
        chefs.add(new Chef(1L, "Gordon", "Ramsay", "Famous chef", List.of(pasta, tacos)));
        chefs.add(new Chef(2L, "Jamie", "Oliver", "Well known chef", List.of(sushi)));
    }
}
