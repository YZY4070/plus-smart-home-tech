package ru.yandex.practicum.telemetry.collector.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.*;
import ru.yandex.practicum.telemetry.collector.model.sensor.*;

@Component
public class SensorEventMapper {
    public SensorEventAvro toAvro(SensorEvent event) {
        Object payload = switch (event) {
            case ClimateSensorEvent climateSensorEvent -> toClimateSensorAvro((ClimateSensorEvent) event);
            case LightSensorEvent lightSensorEvent -> toLightSensorAvro((LightSensorEvent) event);
            case MotionSensorEvent motionSensorEvent -> toMotionSensorAvro((MotionSensorEvent) event);
            case SwitchSensorEvent switchSensorEvent -> toSwitchSensorAvro((SwitchSensorEvent) event);
            case TemperatureSensorEvent temperatureSensorEvent ->
                    toTemperatureSensorAvro((TemperatureSensorEvent) event);
            default -> throw new IllegalArgumentException("Неизвестный тип события: " + event.getClass().getName());
        };

        return SensorEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setId(event.getId())
                .setTimestamp(event.getTimestamp())
                .setPayload(payload)
                .build();
    }

    private ClimateSensorAvro toClimateSensorAvro(ClimateSensorEvent event) {
        return ClimateSensorAvro.newBuilder()
                .setTemperatureC(event.getTemperatureC())
                .setHumidity(event.getHumidity())
                .setCo2Level(event.getCo2Level())
                .build();
    }

    private LightSensorAvro toLightSensorAvro(LightSensorEvent event) {
        return LightSensorAvro.newBuilder()
                .setLinkQuality(event.getLinkQuality())
                .setLuminosity(event.getLuminosity())
                .build();
    }

    private MotionSensorAvro toMotionSensorAvro(MotionSensorEvent event) {
        return MotionSensorAvro.newBuilder()
                .setLinkQuality(event.getLinkQuality())
                .setMotion(event.isMotion())
                .setVoltage(event.getVoltage())
                .build();
    }

    private SwitchSensorAvro toSwitchSensorAvro(SwitchSensorEvent event) {
        return SwitchSensorAvro.newBuilder()
                .setState(event.isState())
                .build();
    }

    private TemperatureSensorAvro toTemperatureSensorAvro(TemperatureSensorEvent event) {
        return TemperatureSensorAvro.newBuilder()
                .setTemperatureC(event.getTemperatureC())
                .setTemperatureF(event.getTemperatureF())
                .build();
    }
}
