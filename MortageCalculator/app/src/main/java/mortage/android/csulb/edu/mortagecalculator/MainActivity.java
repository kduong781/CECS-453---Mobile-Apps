package mortage.android.csulb.edu.mortagecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView interestText;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        interestText = (TextView) findViewById(R.id.interestRate);
        interestText.setText("Interest Rate: " + seekBar.getProgress() + "%"); // updates slider value text
        //retrieves value from slider
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = seekBar.getProgress();
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                progressValue = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                interestText.setText("Interest Rate: " + progressValue + "%");
            }
        });

    }

    public void calculate(View v) {
        int interest = 0, loanTerm = 0;
        float tax = 0, monthly = 0;
        TextView monthlyPayment = (TextView) findViewById(R.id.monthlyPayment);
        switch(v.getId()) {
            case R.id.button:
                RadioButton r1 = (RadioButton) findViewById(R.id.defaultRadio);
                RadioButton r2 = (RadioButton) findViewById(R.id.defaultRadio2);
                RadioButton r3 = (RadioButton) findViewById(R.id.defaultRadio3);
                CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
                editText = (EditText) findViewById(R.id.editText);  //gets amount borrowed
                if(editText.getText().length() == 0) {
                    Toast.makeText(this, "Please enter a number", Toast.LENGTH_LONG).show();
                    return;
                }
                float principal = Float.parseFloat(editText.getText().toString());
                tax =  principal/1000;
                seekBar = (SeekBar) findViewById(R.id.seekBar1);
                interest = seekBar.getProgress(); // gets interest rate from seekbar


                if(r1.isChecked()) {  // gets loan term
                    loanTerm = 15;
                } else if(r2.isChecked()) {
                    loanTerm = 20;
                } else {
                    loanTerm = 30;
                }

                if(interest != 0) {
                    //text.setText(String.valueOf(ConverterUtil.convertCelsius(input)));
                    monthly = CalcUtil.interestPayment(principal, interest, loanTerm);
                } else {
                    monthly = CalcUtil.monthlyPayment(principal, loanTerm);
                }

                if(checkBox.isChecked()) {
                    monthly = monthly + tax;
                }

                monthlyPayment.setText("Monthly Payment: " + String.valueOf(monthly));
                break;
        }
    }
}
