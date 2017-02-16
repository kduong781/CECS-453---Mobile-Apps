package temperature.android.csulb.edu.temperatureconverter;

/**
 * Created by Kevin on 2/14/2017.
 */

public class ConverterUtil {

    //converts to celsius
    public static float convertFahrenheit(float fahrenheit) {
        return ((fahrenheit-32)*5/9);
    }

    //converts to fahrenheit
    public static float convertCelsius(float celsius) {
        return ((celsius*9)/5)+ 32;
    }
}
