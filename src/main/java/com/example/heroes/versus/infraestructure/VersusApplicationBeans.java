package com.example.heroes.versus.infraestructure;

import com.example.heroes.heroes.application.find.HeroFinder;
import com.example.heroes.shared.domain.event.EventBus;
import com.example.heroes.versus.application.create.VersusCreator;
import com.example.heroes.versus.domain.VersusRepository;
import com.example.heroes.villains.application.find.VillainFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VersusApplicationBeans {

    @Bean
    public VersusCreator versusCreator(VersusRepository versusRepository, EventBus eventBus, HeroFinder heroFinder, VillainFinder villainFinder) {
        return new VersusCreator(versusRepository, eventBus, heroFinder, villainFinder);
    }
}
