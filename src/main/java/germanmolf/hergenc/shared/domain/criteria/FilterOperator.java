package germanmolf.hergenc.shared.domain.criteria;

public enum FilterOperator {

    EQUAL("="),
    NOT_EQUAL("!="),
    GT(">"),
    LT("<"),
    CONTAINS("contains"),
    NOT_CONTAINS("not_contains"),
    IN("in"),
    NOT_IN("not_in");

    private final String operator;

    FilterOperator(String operator) {
        this.operator = operator;
    }

    public static FilterOperator fromValue(String value) {
        return switch (value) {
            case "!=" -> NOT_EQUAL;
            case ">" -> GT;
            case "<" -> LT;
            case "contains" -> CONTAINS;
            case "not_contains" -> NOT_CONTAINS;
            case "in" -> IN;
            case "not_in" -> NOT_IN;
            default -> EQUAL;
        };
    }

    public String value() {
        return operator;
    }
}
