package basketball.android.csulb.edu.basketball;

import android.app.Activity;
import android.content.Context;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private static final String TAG = "com.example.accelerometer.MainActivity";
    private PowerManager.WakeLock wakeLock;
    private SimulationView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        view = new SimulationView(ctx);
        view.startSimulation();
        setContentView(view);
        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, TAG);

    }

    protected void onResume() {
        super.onResume();
        wakeLock.acquire();
        view.startSimulation();
    }

    protected void onPause() {
        super.onPause();
        wakeLock.release();
        view.stopSimulation();
    }


}

