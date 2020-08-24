import static java.lang.System.*;

/**
 *Java Class to generate GCD
 */
public class GreatestCommonFactor{

    private int gcd(int element, int firstElement)
    {
        if ( 0 == element )
            return firstElement;
        return gcd(firstElement % element, element);
    }

    /**
     * @param arr
     * @param length
     * @return
     */
    public int highestCommonFactor(int[] arr , int length)
    {
        int firstElement = arr[0];
        for (int i = 1; i < length; i++)
            firstElement = gcd(arr[i], firstElement);

        return firstElement;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        int[] arr = { 4,1,2,3 };
        int length = arr.length;
        GreatestCommonFactor obj=new GreatestCommonFactor();
        out.println(obj.highestCommonFactor(arr, length));
    }
}