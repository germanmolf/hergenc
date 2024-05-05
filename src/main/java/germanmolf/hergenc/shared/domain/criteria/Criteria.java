package germanmolf.hergenc.shared.domain.criteria;

import java.util.ArrayList;
import java.util.List;

public final class Criteria {

    private List<Filter> filters;
    private Order order;
    private Integer limit;
    private Integer start;

    public Criteria() {
        filters = new ArrayList<>();
        order = Order.none();
        limit = Integer.MAX_VALUE;
        start = 0;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public Criteria setFilter(String field, String operator, String value) {
        filters.add(new Filter(field, operator, value));
        return this;
    }

    public Criteria setFilters(List<Filter> filters) {
        this.filters = filters;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Criteria setOrder(String orderBy, String orderType) {
        order = new Order(orderBy, orderType);
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public Criteria setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getStart() {
        return start;
    }

    public Criteria setStart(Integer start) {
        this.start = start;
        return this;
    }

}
