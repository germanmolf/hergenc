package germanmolf.hergenc.versus.domain;

import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.shared.domain.AggregateRoot;
import germanmolf.hergenc.villains.domain.VillainId;

import java.util.Objects;

public final class Versus extends AggregateRoot {

    private final VersusId id;
    private final HeroId heroId;
    private final VillainId villainId;
    private final VersusDefeated defeated;

    public Versus(String id, String heroId, String villainId, String defeated) {
        this.id = new VersusId(id);
        this.heroId = new HeroId(heroId);
        this.villainId = new VillainId(villainId);
        this.defeated = VersusDefeated.fromValue(defeated);
    }

    public static Versus create(String id, String heroId, String villainId, String defeated) {
        Versus versus = new Versus(id, heroId, villainId, defeated);
        versus.record(new VersusCreatedEvent(id, heroId, villainId, defeated));
        return versus;
    }

    public VersusId id() {
        return id;
    }

    public HeroId heroId() {
        return heroId;
    }

    public VillainId villainid() {
        return villainId;
    }

    public VersusDefeated defeated() {
        return defeated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Versus versus = (Versus) o;
        return Objects.equals(id, versus.id) && Objects.equals(heroId, versus.heroId) && Objects.equals(villainId, versus.villainId) && Objects.equals(defeated, versus.defeated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, heroId, villainId, defeated);
    }

    @Override
    public String toString() {
        return "Versus{" +
                "id=" + id +
                ", heroId=" + heroId +
                ", villainId=" + villainId +
                ", defeated=" + defeated +
                '}';
    }
}
