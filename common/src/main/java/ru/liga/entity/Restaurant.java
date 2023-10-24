package ru.liga.entity;

import lombok.*;
import lombok.experimental.Accessors;
import ru.liga.enums.KitchenStatus;

import javax.persistence.*;

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

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private KitchenStatus kitchenStatus;
}
