package com.example.heroes.versus.infraestructure;

import com.example.heroes.heroes.application.find.HeroFinder;
import com.example.heroes.shared.domain.event.EventBus;
import com.example.heroes.versus.application.create.VersusCreator;
import com.example.heroes.versus.application.find.VersusFinder;
import com.example.heroes.versus.application.find.VersusSearcher;
import com.example.heroes.versus.domain.VersusRepository;
import com.example.heroes.villains.application.find.VillainFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VersusApplicationBeans {

    @Bean
    public VersusCreator versusCreator(VersusRepository repository, EventBus eventBus, HeroFinder heroFinder, VillainFinder villainFinder) {
        return new VersusCreator(repository, eventBus, heroFinder, villainFinder);
    }

    @Bean
    public VersusSearcher versusSearcher(VersusRepository repository) {
        return new VersusSearcher(repository);
    }

    @Bean
    public VersusFinder versusFinder(VersusRepository repository) {
        return new VersusFinder(repository);
    }
}
