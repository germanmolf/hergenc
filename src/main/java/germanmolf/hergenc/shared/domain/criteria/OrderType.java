package germanmolf.hergenc.shared.domain.criteria;

public enum OrderType {

    ASC("asc"),
    DESC("desc"),
    NONE("none");

    private final String type;

    OrderType(String type) {
        this.type = type;
    }

    public static OrderType fromValue(String value) {
        return switch (value) {
            case "desc" -> DESC;
            default -> ASC;
        };
    }

    public boolean isAsc() {
        return this == ASC;
    }

    public String value() {
        return type;
    }
}
