package ru.yandex.practicum.telemetry.collector.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.telemetry.collector.handler.EventHandler;
import ru.yandex.practicum.telemetry.collector.model.hub.HubEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.SensorEvent;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class CollectorController {
    private final EventHandler<HubEvent> hubEventService;
    private final EventHandler<SensorEvent> sensorEventService;

    @PostMapping("/hubs")
    public void sendHubEvent(@RequestBody @Valid HubEvent event) {
        log.info("Получен запрос на обработку события от хаба: {}", event);
        hubEventService.handle(event);
    }

    @PostMapping("/sensors")
    public void sendSensorEvent(@RequestBody @Valid SensorEvent event) {
        log.info("Получен запрос на обработку события от датчика: {}", event);
        sensorEventService.handle(event);
    }
}
