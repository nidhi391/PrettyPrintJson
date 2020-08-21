import org.codehaus.jackson.map.annotate.JsonFilter;

@JsonFilter("myFilter")
public class JsonPrettyPrintDemoBean {


    int id ;
    InnerObjects type;

    AddressObject addressLineDetail;
    InnerObjects provinceOrState;
    String cityOrTown;

    InnerObjects country;
    String postalCode;

    String lastUpdated;

    public int getId() {
        return id;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public InnerObjects getType() {
        return type;
    }

    public AddressObject getAddressLineDetail() {
        return addressLineDetail;
    }

    public InnerObjects getProvinceOrState() {
        return provinceOrState;
    }

    public String getCityOrTown() {
        return cityOrTown;
    }

    public InnerObjects getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }


    @Override
    public String toString() {
        return "JsonPrettyPrintDemoBean{" +
                "id=" + id +
                ", type=" + type +
                ", addressLineDetail=" + addressLineDetail +
                ", provinceOrState=" + provinceOrState +
                ", cityOrTown='" + cityOrTown + '\'' +
                ", country=" + country +
                ", postalCode='" + postalCode + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }


}