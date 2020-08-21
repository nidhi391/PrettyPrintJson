/**
 * Java class for inner Fields in Address json
 */
public class InnerObjects {

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "innerObjects{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
