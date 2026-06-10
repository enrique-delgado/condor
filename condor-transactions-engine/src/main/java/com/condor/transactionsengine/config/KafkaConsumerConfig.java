package com.condor.transactionsengine.config;

import com.condor.event.TransactionInitiatedEventProto;
import com.condor.transactionsengine.integration.ProtobufDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, TransactionInitiatedEventProto.TransactionInitiatedEvent> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", "transactions-engine-group-three");
        props.put("key.deserializer", org.apache.kafka.common.serialization.StringDeserializer.class);
        props.put("value.deserializer", ErrorHandlingDeserializer.class);
        props.put("spring.deserializer.value.delegate.class", ProtobufDeserializer.class.getName());
        props.put("protobuf.type", TransactionInitiatedEventProto.TransactionInitiatedEvent.class.getName());

        return new DefaultKafkaConsumerFactory<>(props);
    }
}
