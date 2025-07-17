package ru.yandex.practicum.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    @NotNull
    UUID orderId;

    UUID shoppingCartId;

    Map<UUID, Long> products;

    UUID paymentId;

    UUID deliveryId;

    String state;

    Double deliveryWeight;

    Double deliveryVolume;

    Boolean fragile;

    Double totalPrice;

    Double deliveryPrice;

    Double productPrice;
}
