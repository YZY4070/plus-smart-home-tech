package ru.yandex.practicum.feign;

import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;
import ru.yandex.practicum.dto.warehouse.*;

import java.util.Map;
import java.util.UUID;

@FeignClient(name = "warehouse", path = "/api/v1/warehouse")
public interface WarehouseClient {
    @GetMapping("/address")
    AddressDto getWarehouseAddress() throws FeignException;

    @PostMapping("/add")
    void addProductToWarehouse(@RequestBody @Valid AddProductToWarehouseRequest request) throws FeignException;

    @PostMapping("/check")
    BookedProductsDto checkProductQuantity(@RequestBody ShoppingCartDto shoppingCart) throws FeignException;

    @PutMapping
    void createNewProductInWarehouse(@RequestBody NewProductInWarehouseRequest newProductInWarehouseRequest);

    @PostMapping("/shipped")
    void shipToDelivery(@Valid @RequestBody ShippedToDeliveryRequest request) throws FeignException;

    @PostMapping("/return")
    void acceptReturn(@RequestBody Map<UUID, Long> returnedProducts) throws FeignException;

    @PostMapping("/assembly")
    BookedProductsDto assembleProducts(@Valid @RequestBody AssemblyProductsForOrderRequest request) throws FeignException;

}
