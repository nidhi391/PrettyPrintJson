public class AddressObject {

    String line1;
    String line2;

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    @Override
    public String toString() {
        return "addressObject{" +
                "line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                '}';
    }
}
