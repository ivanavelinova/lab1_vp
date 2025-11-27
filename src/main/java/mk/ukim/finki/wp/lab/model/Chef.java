package mk.ukim.finki.wp.lab.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.wp.lab.model.Dish;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class Chef {

    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private List<Dish> dishes = new ArrayList<>();

    public Chef(Long id, String firstName, String lastName, String bio) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.dishes = new ArrayList<>();
    }

    public Chef(Long id, String firstName, String lastName, String bio, List<Dish> dishes) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.dishes = dishes;
    }
}
