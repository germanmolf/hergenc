package com.example.heroes.shared;

import java.util.List;

public interface EventBus {

    void publish(List<DomainEvent> events);
    
}
