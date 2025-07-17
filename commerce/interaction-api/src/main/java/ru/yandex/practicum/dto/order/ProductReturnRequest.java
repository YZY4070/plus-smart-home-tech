package ru.yandex.practicum.dto.order;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductReturnRequest {
    UUID orderId;

    Map<UUID, Long> products;
}
