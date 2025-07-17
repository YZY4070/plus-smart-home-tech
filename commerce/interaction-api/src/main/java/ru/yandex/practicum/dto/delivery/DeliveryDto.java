package ru.yandex.practicum.dto.delivery;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.dto.warehouse.AddressDto;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DeliveryDto {
    UUID deliveryId;

    AddressDto fromAddress;

    AddressDto toAddress;

    UUID orderId;

    DeliveryState deliveryState;

    Double weight;

    Double volume;

    boolean fragile;
}