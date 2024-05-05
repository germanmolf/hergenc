package germanmolf.hergenc.villains.domain;

import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.shared.domain.criteria.Filter;

import java.util.Arrays;
import java.util.List;

public final class VillainCriteriaMother {

    public static Criteria nameAndPowerContains(String name, String power) {
        Filter nameFilter = new Filter("name", "contains", name);
        Filter powerFilter = new Filter("power", "contains", power);
        List<Filter> filters = Arrays.asList(nameFilter, powerFilter);
        return new Criteria().setFilters(filters);
    }
}
