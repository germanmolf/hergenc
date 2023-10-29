package com.example.heroes.versus.domain;

import com.example.heroes.versus.application.create.CreateVersusRequest;

public final class VersusCreatedEventMother {

    public static VersusCreatedEvent fromRequest(CreateVersusRequest request) {
        return new VersusCreatedEvent(request.id(), request.heroId(), request.villainId(), request.defeated());
    }
}
