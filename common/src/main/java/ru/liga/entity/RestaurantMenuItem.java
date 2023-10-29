package ru.liga.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "restaurant_menu_items")
public class RestaurantMenuItem {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant_id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;
}
