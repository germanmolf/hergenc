package com.example.heroes.heroes.application.find;

import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.shared.domain.Injectable;
import com.example.heroes.shared.domain.criteria.Criteria;

import java.util.List;

@Injectable
public final class HeroSearcher {

    private final HeroRepository repository;

    public HeroSearcher(HeroRepository repository) {
        this.repository = repository;
    }

    public List<HeroResponse> search(Criteria criteria) {
        return repository.search(criteria)
                .stream().map(HeroResponse::fromAggregate)
                .toList();
    }
}
