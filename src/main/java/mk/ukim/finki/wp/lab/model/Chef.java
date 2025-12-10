package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chefs")
@Getter
@Setter
@NoArgsConstructor
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String bio;

    @OneToMany(mappedBy = "chef", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Dish> dishes = new ArrayList<>();


    // Конструктор без id
    public Chef(String firstName, String lastName, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

    // Конструктор со листа на јадења
    public Chef(String firstName, String lastName, String bio, List<Dish> dishes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.dishes = dishes;
        for (Dish d : dishes) {
            d.setChef(this);
        }
    }
}
