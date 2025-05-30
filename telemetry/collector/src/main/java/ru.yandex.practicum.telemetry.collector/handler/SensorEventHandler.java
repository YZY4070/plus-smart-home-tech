package ru.yandex.practicum.telemetry.collector.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.telemetry.collector.kafka.KafkaEventProducer;
import ru.yandex.practicum.telemetry.collector.mapper.SensorEventMapper;
import ru.yandex.practicum.telemetry.collector.model.sensor.SensorEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorEventHandler implements EventHandler<SensorEvent> {
    private final KafkaEventProducer producer;
    private final SensorEventMapper mapper;

    @Value("telemetry.sensors.v1")
    private String topic;

    @Override
    public void handle(SensorEvent event) {
        SensorEventAvro sensorEventAvro = mapper.toAvro(event);
        log.info("Hub event received: {}: {}", topic, sensorEventAvro);
        producer.send(topic, sensorEventAvro.getHubId(), sensorEventAvro.getTimestamp(), sensorEventAvro);
    }
}
