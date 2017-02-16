package mortage.android.csulb.edu.mortagecalculator;

/**
 * Created by Kevin on 2/14/2017.
 */

public class CalcUtil {
    public static float interestPayment(float p, float j, float n) {
        j = j/100;
        return p*(j/(1-(float) Math.pow(1+j,-n)));

    }

    public static float monthlyPayment(float p, float n) {
        return (float) (p/n);
    }
}
