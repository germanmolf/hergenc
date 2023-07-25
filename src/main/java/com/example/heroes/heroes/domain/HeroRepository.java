package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface HeroRepository {

    void save(Hero hero);

    Optional<Hero> search(HeroId id);

    List<Hero> searchAll();

    List<Hero> search(Criteria criteria);

}
