package temperature.android.csulb.edu.temperatureconverter;

import android.app.Activity;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.editText);
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button:
                try {
                    RadioButton celsius = (RadioButton) findViewById(R.id.radioButton);
                    RadioButton fahrenheit = (RadioButton) findViewById(R.id.radioButton2);
                    if (text.getText().length() == 0) {
                        Toast.makeText(this, "Please enter a number", Toast.LENGTH_LONG).show();
                        return;
                    }
                    float input = Float.parseFloat(text.getText().toString()); // parses string into float
                    // checks which radio button is checked
                    if (celsius.isChecked()) {
                        text.setText(String.valueOf(ConverterUtil.convertCelsius(input)));
                        celsius.setChecked(false);
                        fahrenheit.setChecked(true);
                    } else {
                        text.setText(String.valueOf(ConverterUtil.convertFahrenheit(input)));
                        celsius.setChecked(true);
                        fahrenheit.setChecked(false);
                    }
                    break;
                } catch(Exception e) {
                    Toast.makeText(this, "Please enter a number", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

