
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class GreatestCommonFactorTest {

    @Test
    void testHighestCommonFactor() {
        GreatestCommonFactor factor= new GreatestCommonFactor();
        int[] arr = { 111, 259 };
        int len = arr.length;
        assertEquals ( 37 , factor.highestCommonFactor ( arr,len ) );
    }

    @Test
    void main() {
        GreatestCommonFactor.main (null);
        assertEquals ( 1 , 1);
    }
}