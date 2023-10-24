package ru.liga.enums;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum CourierStatus {
    PENDING,
    PICKING,
    DELIVERING,
    COMPLETE,
    DENIED,
    REFUNDED;
}
