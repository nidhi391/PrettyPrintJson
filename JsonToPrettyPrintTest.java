import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
class JsonToPrettyPrintTest {
    @Test
    void main() throws IOException {
        String json = "{\"id\":\"2\",\"type\":{\"code\":\"2\",\"name\":\"Postal Address\"},\"cityOrTown\":\"City 2\",\"country\":{\"code\":\"LB\",\"name\":\"Lebanon\"},\"postalCode\":\"2345\",\"lastUpdated\":\"2017-06-21T00:00:00.000Z\"}";
        JsonToPrettyPrint.main ( null );
        assertEquals (json.contains ( "Lebanon" ), true);

    }
    @Test
    void testAddressValidation() throws IOException {
        String json = "{\"id\":\"2\",\"type\":{\"code\":\"2\",\"name\":\"Postal Address\"},\"cityOrTown\":\"City 2\",\"country\":{\"code\":\"ZA\",\"name\":\"Lebanon\"},\"postalCode\":\"2345\",\"lastUpdated\":\"2017-06-21T00:00:00.000Z\"}";
        JsonToPrettyPrint.main ( null );
        assertEquals (json.contains ( "provinceOrState" ), false);

    }

    @Test
    void testAddressValidationCondition3() throws IOException {

        JsonPrettyPrintDemoBean bean= new JsonPrettyPrintDemoBean ();
        InnerObjects innerObj= new InnerObjects ();
        innerObj.setName ( null );
        bean.setCountry ( innerObj );
        bean.setPostalCode ( "1234" );
        JsonToPrettyPrint.validateAddress ( bean );
        assertEquals ("Validation of CountryName :[]", "Validation of CountryName :[]");

    }
}