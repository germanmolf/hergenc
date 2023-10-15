package com.example.heroes.villains.infraestructure;

import com.example.heroes.shared.domain.event.EventBus;
import com.example.heroes.villains.application.create.VillainCreator;
import com.example.heroes.villains.application.find.VillainFinder;
import com.example.heroes.villains.application.find.VillainsSearcher;
import com.example.heroes.villains.domain.VillainRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VillainsApplicationConfiguration {

    @Bean
    public VillainCreator villainCreator(VillainRepository repository, EventBus eventBus) {
        return new VillainCreator(repository, eventBus);
    }

    @Bean
    public VillainFinder villainFinder(VillainRepository repository) {
        return new VillainFinder(repository);
    }

    @Bean
    public VillainsSearcher villainsSearcher(VillainRepository repository) {
        return new VillainsSearcher(repository);
    }
}
