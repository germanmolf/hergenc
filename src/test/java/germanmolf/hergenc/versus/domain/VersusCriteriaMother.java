package germanmolf.hergenc.versus.domain;

import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.shared.domain.criteria.Filter;
import germanmolf.hergenc.versus.domain.VersusDefeated;

import java.util.Arrays;
import java.util.List;

public final class VersusCriteriaMother {

    public static Criteria heroAndVillainNoneDefeated(String heroId, String villainId) {
        Filter heroIdFilter = new Filter("heroId", "=", heroId);
        Filter villainIdFilter = new Filter("villainId", "=", villainId);
        Filter defeatedFilter = new Filter("defeated", "=", VersusDefeated.NONE.value());
        List<Filter> filters = Arrays.asList(heroIdFilter, villainIdFilter, defeatedFilter);
        return new Criteria().setFilters(filters);
    }

}
