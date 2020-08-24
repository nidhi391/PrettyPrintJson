import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.map.type.TypeFactory;

import java.io.IOException;
import java.util.List;

import static java.lang.String.*;
import static java.lang.System.*;
import static org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter.*;


/**
 * Class to print json in PrettyPrint format and do validation on the Json String
 */
public class JsonToPrettyPrint {

    public static void main(String[] args) throws IOException {
        String jsonString = "[{\"id\":\"1\",\"type\":{\"code\":\"1\",\"name\":\"Physical Address\"},\"addressLineDetail\":{\"line1\":\"Address 1\",\"line2\":\"Line 2\"},\"provinceOrState\":{\"code\":\"5\",\"name\":\"Eastern Cape\"},\"cityOrTown\":\"City 1\",\"country\":{\"code\":\"ZA\",\"name\":\"South Africa\"},\"postalCode\":\"1234\",\"lastUpdated\":\"2015-06-21T00:00:00.000Z\"}" +
                ",{\"id\":\"2\",\"type\":{\"code\":\"2\",\"name\":\"Postal Address\"},\"cityOrTown\":\"City 2\",\"country\":{\"code\":\"LB\",\"name\":\"Lebanon\"},\"postalCode\":\"23456\",\"lastUpdated\":\"2017-06-21T00:00:00.000Z\"}," +
                "{\"id\":\"3\",\"type\":{\"code\":\"5\",\"name\":\"Business Address\"},\"addressLineDetail\":{\"line1\":\"Address 3\",\"line2\":\"\"},\"cityOrTown\":\"City 3\",\"country\":{\"code\":\"ZA\",\"name\":\"South Africa\"},\"postalCode\":\"3456\",\"lastUpdated\":\"2018-06-13T00:00:00.000Z\"}]";
        ObjectMapper objectMapper= new ObjectMapper();
        String addressType = "Physical Address";

        addressOfCertainType( addressType ,
                              objectMapper.readValue(jsonString, TypeFactory.defaultInstance().constructCollectionType(List.class, JsonPrettyPrintDemoBean.class)));

        SimpleBeanPropertyFilter theFilter = getTheFilter();

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("myFilter", theFilter);

        out.println("JSON  mapping to POJO with pretty print");

        for (JsonPrettyPrintDemoBean bean : objectMapper.<List<JsonPrettyPrintDemoBean>>readValue(jsonString, TypeFactory.defaultInstance().constructCollectionType(List.class, JsonPrettyPrintDemoBean.class))) {

            String fieldValue = validateAddress(bean);

            if(StringUtils.isNotEmpty(fieldValue) && StringUtils.equals("Address Validation is successful", fieldValue))
                out.println ( objectMapper.writerWithDefaultPrettyPrinter ( ).withFilters ( filters ).writeValueAsString ( bean ) );
            else new Error ( fieldValue ).printStackTrace ( );
        }


    }

    /**
     * Method to DeSerialize specific parameters
     * @return
     */
    private static SimpleBeanPropertyFilter getTheFilter() {
        return serializeAllExcept("id","lastUpdated");
    }

    /**
     * Method to validate Address Json
     * @param bean
     * @return
     */
    public static String validateAddress(JsonPrettyPrintDemoBean bean) {
        /**
         * A valid address must consist of a numeric postal code, a country
         * and at least one address line that is not blank or null.
         */
        if(!((bean.getPostalCode().matches("[0-9+]{4}"))))
            return  format("Validation of Postal Code :%s" , bean.getPostalCode());
        if(!( ( null != bean.getAddressLineDetail ( ) ) && ( StringUtils.isNotEmpty ( bean.getAddressLineDetail ( ).getLine1 ( ) )
                || StringUtils.isNotEmpty ( bean.getAddressLineDetail ( ).getLine2 ( ) ) ) ) )
            return format("Validation of AddressDetail :%s", bean.getAddressLineDetail());
        if (!(StringUtils.isNotEmpty ( bean.getCountry ().getName () )))
            return format ( "Validation of CountryName :%s",
                            bean.getCountry ().getName () );

        if (StringUtils.equals("ZA", bean.getCountry().getCode()))
            if (null == bean.getProvinceOrState())
                return format("Validation of Province Fail for code ZA :%s", bean.getProvinceOrState());

        return "Address Validation is successful";
    }


    /**
     * Method to get Json value for the specified address Type
     * @param addressType
     * @param beanObject
     */
    private static void addressOfCertainType(String addressType, List<JsonPrettyPrintDemoBean> beanObject) {
        for (JsonPrettyPrintDemoBean addressVal : beanObject ) {
            if(StringUtils.equals((addressVal.getType().getName()), addressType)){
                out.println("Print an address of a certain type:-" + addressType);
                out.println(addressVal.toString());
            }
        }
    }

}
