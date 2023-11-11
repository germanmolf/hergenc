package com.example.heroes.versus.infraestructure.persistence;

import com.example.heroes.versus.domain.Versus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "versus")
public final class VersusJpa {

    @Id
    private String id;
    private String heroId;
    private String villainId;
    private String defeated;

    public static VersusJpa fromAggregate(Versus versus) {
        return new VersusJpa(versus.getId().value(),
                versus.getHeroId().value(),
                versus.getVillainId().value(),
                versus.getDefeated().value());
    }

    public static Versus fromJpaEntity(VersusJpa versusJpa) {
        return new Versus(versusJpa.getId(), versusJpa.getHeroId(), versusJpa.getVillainId(), versusJpa.getDefeated());
    }
}
