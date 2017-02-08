package lab1.android.csulb.edu.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/**
 * Kevin Duong
 * 011715000
 */
public class GetName extends Activity implements View.OnClickListener {

    EditText name;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // premade
        setContentView(R.layout.name_getter); // sets view to the layout

        name = (EditText) this.findViewById(R.id.editText); // gets the editText from current layout
        button = (Button) this.findViewById(R.id.button); // gets the button from current layout
        button.setOnClickListener(this); //links onclicklistener to the button
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(this, Lab1Activity.class); // sends message to lab1activity
        myIntent.putExtra("uname",name.getText().toString()); // creates uname string in the intent
        this.startActivity(myIntent); // starts intent
    }
}
