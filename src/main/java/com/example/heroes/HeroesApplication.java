package com.example.heroes;

import com.example.heroes.shared.domain.Injectable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(includeFilters = @ComponentScan.Filter(classes = Injectable.class))
public class HeroesApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroesApplication.class, args);
    }
}
