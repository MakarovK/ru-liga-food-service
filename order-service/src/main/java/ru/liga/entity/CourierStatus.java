package ru.liga.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum CourierStatus {
    PENDING, PICKING, DELIVERING, COMPLETE,
            DENIED, REFUNDED;
}
