package ru.yandex.practicum.kafka;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;
import serializer.AvroSerializer;

import java.time.Duration;
import java.util.Properties;

@Component
public class KafkaEventProducer implements AutoCloseable {
    private final KafkaProducer<String, SpecificRecordBase> kafkaProducer;

    private Properties producerProperties() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class.getName());
        return props;
    }

    public KafkaEventProducer() {
        this.kafkaProducer = new KafkaProducer<>(producerProperties());
    }

    public void send(String topic, String key, SpecificRecordBase value) {
        kafkaProducer.send(new ProducerRecord<>(topic, key, value));
        kafkaProducer.flush();
    }

    public void flush() {
        kafkaProducer.flush();
    }

    @Override
    public void close() {
        kafkaProducer.flush();
        kafkaProducer.close(Duration.ofSeconds(5));
    }
}
