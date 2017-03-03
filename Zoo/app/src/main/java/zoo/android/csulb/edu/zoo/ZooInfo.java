package zoo.android.csulb.edu.zoo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ZooInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoo_info);
        TextView phone = (TextView) findViewById(R.id.phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri call = Uri.parse("8888888888");
                Intent callIntent = new Intent(Intent.ACTION_CALL, call);
                startActivity( new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.phone))));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_info) {
            Intent myIntent = new Intent(this, ZooInfo.class);
            this.startActivity(myIntent);
        }
        if(id == R.id.action_uninstall) {
            Uri packageURI = Uri.parse("package:zoo.android.csulb.edu.zoo");
            Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
            this.startActivity(uninstallIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Write your code here
        super.onBackPressed();
    }

}
