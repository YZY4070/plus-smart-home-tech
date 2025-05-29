package ru.yandex.practicum.telemetry.collector.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.telemetry.collector.KafkaEventProducer;
import ru.yandex.practicum.telemetry.collector.mapper.HubEventMapper;
import ru.yandex.practicum.telemetry.collector.model.hub.HubEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class HubEventHandler implements EventHandler<HubEvent> {
    private final KafkaEventProducer producer;
    private final HubEventMapper mapper;

    @Value("telemetry.hub.v1")
    private String topic;

    @Override
    public void handle(HubEvent event) {
        HubEventAvro eventAvro = mapper.toAvro(event);
        log.info("Hub event received: {}: {}", topic, eventAvro);
        producer.send(topic, event.getHubId(), eventAvro);
    }
}
