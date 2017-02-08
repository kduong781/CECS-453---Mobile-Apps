package lab1.android.csulb.edu.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Kevin Duong
 * 011715000
 */
public class Lab1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1);
        Bundle myInput = this.getIntent().getExtras(); // recieve message from intent
        TextView t = new TextView(this); // initialize new textview
        t = (TextView) findViewById(R.id.hw); // set textView to "Hello World text"
        t.setText("Hello " + (myInput.getString("uname"))); //modify text view based on intent name
    }
}
