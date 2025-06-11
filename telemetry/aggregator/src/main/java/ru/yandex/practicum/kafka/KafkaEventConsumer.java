package ru.yandex.practicum.kafka;

import deserializer.SensorEventDeserializer;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

@Component
public class KafkaEventConsumer implements AutoCloseable {
    private final KafkaConsumer<String, SpecificRecordBase> kafkaConsumer;

    private Properties consumerProperties() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "aggregator-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, SensorEventDeserializer.class.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        return props;
    }

    public KafkaEventConsumer() {
        this.kafkaConsumer = new KafkaConsumer<>(consumerProperties());
    }

    public ConsumerRecords<String, SpecificRecordBase> poll(Duration duration) {
        return kafkaConsumer.poll(duration);
    }

    public void subscribe(List<String> topics) {
        kafkaConsumer.subscribe(topics);
    }

    public void commitSync() {
        kafkaConsumer.commitSync();
    }

    public void wakeup() {
        kafkaConsumer.wakeup();
    }

    @Override
    public void close() {
        kafkaConsumer.close();
    }
}
