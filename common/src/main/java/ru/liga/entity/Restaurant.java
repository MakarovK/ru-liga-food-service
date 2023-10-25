package ru.liga.entity;

import lombok.*;
import lombok.experimental.Accessors;
import ru.liga.enums.KitchenStatus;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "address")
    private String address;

    @OneToMany
    @JoinColumn(name = "restaurant_id")
    private List<RestaurantMenuItem> restaurantMenuItems;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private KitchenStatus kitchenStatus;
}
