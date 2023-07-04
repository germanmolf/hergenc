package com.example.heroes.config.heroes;

import com.example.heroes.heroes.application.HeroCreator;
import com.example.heroes.heroes.application.HeroFinder;
import com.example.heroes.heroes.application.HeroesSearcher;
import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.shared.domain.event.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeroesConfiguration {

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
