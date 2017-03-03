package zoo.android.csulb.edu.zoo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Detail extends AppCompatActivity {

    public void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.detail_fragment);
        ImageView img = (ImageView) findViewById(R.id.animalPic);
        TextView name = (TextView) findViewById(R.id.animalName);
        TextView desc = (TextView) findViewById(R.id.animalDesc);
        Bundle args = this.getIntent().getExtras();
        int imgArray[] = {R.drawable.zebra,R.drawable.tiger,R.drawable.lion, R.drawable.penguin, R.drawable.giraffe};
        img.setImageResource(imgArray[args.getInt("pos")]);
        name.setText(args.getString("name"));
        desc.setText(args.getString("desc"));
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


}
