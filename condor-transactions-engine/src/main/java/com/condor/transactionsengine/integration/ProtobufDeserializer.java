package com.condor.transactionsengine.integration;

import com.google.protobuf.Message;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ProtobufDeserializer<T extends Message> implements Deserializer<T> {

    private Class<T> type;

    public ProtobufDeserializer() {
    }

    public ProtobufDeserializer(Class<T> type) {
        this.type = type;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String typeName = (String) configs.get("protobuf.type");
        if (typeName != null) {
            try {
                this.type = (Class<T>) Class.forName(typeName);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Protobuf class not found: " + typeName, e);
            }
        }
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if (type == null) {
            throw new IllegalStateException("Protobuf type not configured");
        }

        try {
            return (T) type.getMethod("parseFrom", byte[].class).invoke(null, data);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing Protobuf message", e);
        }
    }
}
