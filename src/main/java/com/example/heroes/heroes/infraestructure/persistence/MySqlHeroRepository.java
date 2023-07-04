package com.example.heroes.heroes.infraestructure.persistence;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.shared.domain.criteria.Criteria;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class MySqlHeroRepository implements HeroRepository {

    private HashMap<String, Hero> heroes = new HashMap<>();

    @Override
    public void save(Hero hero) {
        heroes.put(hero.getId(), hero);
    }

    @Override
    public Optional<Hero> search(HeroId id) {
        return Optional.ofNullable(heroes.get(id.value()));
    }

    @Override
    public List<Hero> search(Criteria criteria) {
        return heroes.values().stream().toList();
    }
}
