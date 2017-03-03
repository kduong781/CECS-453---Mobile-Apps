package zoo.android.csulb.edu.zoo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
