package ru.yandex.practicum.dto.store;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.dto.store.enums.QuantityState;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class updateStockLevelStateRequest {
    @NotNull
    Long productId;

    @NotNull
    QuantityState quantityState;
}