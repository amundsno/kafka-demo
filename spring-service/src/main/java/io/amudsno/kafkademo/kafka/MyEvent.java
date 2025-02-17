package io.amudsno.kafkademo.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MyEvent(@JsonProperty("message") String message, @JsonProperty("id") int id) {
}
