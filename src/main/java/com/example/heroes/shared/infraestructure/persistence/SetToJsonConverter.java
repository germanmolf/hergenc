package com.example.heroes.shared.infraestructure.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.util.Set;

public class SetToJsonConverter implements AttributeConverter<Set, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Set object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, Set.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
