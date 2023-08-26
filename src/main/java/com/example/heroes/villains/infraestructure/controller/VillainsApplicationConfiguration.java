package com.example.heroes.villains.infraestructure.controller;

import com.example.heroes.shared.domain.event.EventBus;
import com.example.heroes.villains.application.create.VillainCreator;
import com.example.heroes.villains.domain.VillainRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VillainsApplicationConfiguration {

    @Bean
    public VillainCreator villainCreator(VillainRepository repository, EventBus eventBus) {
        return new VillainCreator(repository, eventBus);
    }
}
