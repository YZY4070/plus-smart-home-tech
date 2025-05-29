package ru.yandex.practicum.telemetry.collector.handler;

public interface EventHandler<T> {
    void handle(T event);
}
