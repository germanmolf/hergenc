package germanmolf.hergenc.shared.infraestructure.persistence;

import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.shared.domain.criteria.Filter;
import germanmolf.hergenc.shared.domain.criteria.FilterOperator;
import jakarta.persistence.criteria.*;

import java.util.EnumMap;
import java.util.List;
import java.util.function.BiFunction;

public final class CriteriaConverter<T> {

    private final CriteriaBuilder builder;

    private final EnumMap<FilterOperator, BiFunction<Filter, Root<T>, Predicate>> predicateTransformers =
            new EnumMap<>(FilterOperator.class) {{
                put(FilterOperator.EQUAL, CriteriaConverter.this::equalsPredicateTransformer);
                put(FilterOperator.NOT_EQUAL, CriteriaConverter.this::notEqualsPredicateTransformer);
                put(FilterOperator.GT, CriteriaConverter.this::greaterThanPredicateTransformer);
                put(FilterOperator.LT, CriteriaConverter.this::lowerThanPredicateTransformer);
                put(FilterOperator.CONTAINS, CriteriaConverter.this::containsPredicateTransformer);
                put(FilterOperator.NOT_CONTAINS, CriteriaConverter.this::notContainsPredicateTransformer);
                put(FilterOperator.IN, CriteriaConverter.this::inPredicateTransformer);
                put(FilterOperator.NOT_IN, CriteriaConverter.this::notInPredicateTransformer);
            }};

    private static final String IN_DIVIDER = ",";

    public CriteriaConverter(CriteriaBuilder builder) {
        this.builder = builder;
    }

    public CriteriaQuery<T> convert(Criteria criteria, Class<T> aggregateClass) {
        CriteriaQuery<T> criteriaQuery = builder.createQuery(aggregateClass);
        Root<T> root = criteriaQuery.from(aggregateClass);

        criteriaQuery.where(formatPredicates(criteria.getFilters(), root));

        if (criteria.getOrder().hasOrder()) {
            Path<Object> orderBy = root.get(criteria.getOrder().getOrderBy());
            Order order = criteria.getOrder().getOrderType().isAsc() ? builder.asc(orderBy) : builder.desc(orderBy);
            criteriaQuery.orderBy(order);
        }

        return criteriaQuery;
    }

    public CriteriaQuery<Long> convertForCount(Criteria criteria, Class<T> aggregateClass) {
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(aggregateClass);
        criteriaQuery.select(builder.count(root)).where(formatPredicates(criteria.getFilters(), root));
        return criteriaQuery;
    }

    private Predicate[] formatPredicates(List<Filter> filters, Root<T> root) {
        return filters.stream().map(filter -> formatPredicate(filter, root)).toArray(Predicate[]::new);
    }

    private Predicate formatPredicate(Filter filter, Root<T> root) {
        BiFunction<Filter, Root<T>, Predicate> transformer = predicateTransformers.get(filter.getOperator());
        return transformer.apply(filter, root);
    }

    private Predicate equalsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.equal(root.get(filter.getField()), filter.getValue());
    }

    private Predicate notEqualsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.notEqual(root.get(filter.getField()), filter.getValue());
    }

    private Predicate greaterThanPredicateTransformer(Filter filter, Root<T> root) {
        return builder.greaterThan(root.get(filter.getField()), filter.getValue());
    }

    private Predicate lowerThanPredicateTransformer(Filter filter, Root<T> root) {
        return builder.lessThan(root.get(filter.getField()), filter.getValue());
    }

    private Predicate containsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.like(root.get(filter.getField()), String.format("%%%s%%", filter.getValue()));
    }

    private Predicate notContainsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.notLike(root.get(filter.getField()), String.format("%%%s%%", filter.getValue()));
    }

    private Predicate inPredicateTransformer(Filter filter, Root<T> root) {
        Object[] values = filter.getValue().split(IN_DIVIDER);
        return root.get(filter.getField()).in(values);
    }

    private Predicate notInPredicateTransformer(Filter filter, Root<T> root) {
        Object[] values = filter.getValue().split(IN_DIVIDER);
        return builder.not(root.get(filter.getField()).in(values));
    }
}
