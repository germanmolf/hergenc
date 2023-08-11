package com.example.heroes.shared.application;

import com.example.heroes.shared.domain.event.DomainEvent;
import com.example.heroes.shared.domain.event.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

import java.util.Collections;

import static org.mockito.Mockito.*;

@Tag("UnitTest")
public abstract class UnitTestModule {

    protected EventBus eventBus;

    @BeforeEach
    protected void setUp() {
        eventBus = mock(EventBus.class);
    }

    protected void shouldHavePublished(DomainEvent event) {
        verify(eventBus, atLeastOnce()).publish(Collections.singletonList(event));
    }

    protected void shouldNotHavePublished(DomainEvent event) {
        verify(eventBus, never()).publish(Collections.singletonList(event));
    }
}
