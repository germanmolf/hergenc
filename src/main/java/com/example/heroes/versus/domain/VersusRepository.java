package com.example.heroes.versus.domain;

import com.example.heroes.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface VersusRepository {

    void save(Versus versus);

    Optional<Versus> search(VersusId id);

    List<Versus> search(Criteria criteria);

    Long count(Criteria criteria);

}
