package com.rabbitmq.rr.producer_advanced.config;

public enum QueueDefinition {
    FIRST_QUEUE("FIRST-QUEUE-ADVANCED"),
    SECOND_QUEUE("SECOND-QUEUE-ADVANCED"),
    DIRECT_EXCHANGE("DIRECT-EXCHANGE-ADVANCED"),
    FIRST_BINDING_KEY("TO-FIRST-QUEUE"),
    SECOND_BINDING_KEY("TO-SECOND-QUEUE"),
    DLQ_EXCHANGE("DLQ-EXCHANGE"),
    DLQ_QUEUE("DLQ-QUEUE"),
    DLQ_BINDING_KEY("TO-DLQ");

    private final String value;

    QueueDefinition(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
