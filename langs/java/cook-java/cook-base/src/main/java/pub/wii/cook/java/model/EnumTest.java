package pub.wii.cook.java.model;

public enum EnumTest {
    STRING(0),
    INTEGER(1),
    LONG(2),
    DOUBLE(3),
    FLOAT(4),
    FACET(5);
    private final int value;

    private EnumTest(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EnumTest findByValue(int value) {
        switch (value) {
            case 0:
                return STRING;
            case 1:
                return INTEGER;
            case 2:
                return LONG;
            case 3:
                return DOUBLE;
            case 4:
                return FLOAT;
            case 5:
                return FACET;
            default:
                return null;
        }
    }
}
