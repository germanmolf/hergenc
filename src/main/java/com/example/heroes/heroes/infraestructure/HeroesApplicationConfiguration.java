package com.example.heroes.heroes.infraestructure;

import com.example.heroes.heroes.application.create.HeroCreator;
import com.example.heroes.heroes.application.find.HeroFinder;
import com.example.heroes.heroes.application.find.HeroesSearcher;
import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.shared.domain.event.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeroesApplicationConfiguration {

    @Bean
    public HeroFinder heroFinder(HeroRepository heroRepository) {
        return new HeroFinder(heroRepository);
    }

    @Bean
    public HeroesSearcher heroesSearcher(HeroRepository heroRepository) {
        return new HeroesSearcher(heroRepository);
    }

    @Bean
    public HeroCreator heroCreator(HeroRepository heroRepository, EventBus eventBus) {
        return new HeroCreator(heroRepository, eventBus);
    }
}
