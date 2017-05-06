package basketball.android.csulb.edu.basketball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

import java.util.List;

/**
 * Created by Kevin on 3/15/2017.
 */

public class SimulationView extends View implements SensorEventListener{
    private Bitmap mField;
    private Bitmap mBasket;
    private Bitmap mBitmap;
    private Display mDisplay;
    private Particle mBall;
    private static final int BALL_SIZE = 64;
    private static final int BASKET_SIZE = 80;

    private SensorManager sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
    private float mXOrigin;
    private float mYOrigin;
    private float mHorizontalBound;
    private float mVerticalBound;
    private float mSensorX, mSensorY, mSensorZ;
    private long mSensorTimeStamp;

    public SimulationView(Context context) {
        super(context);
        Bitmap ball = BitmapFactory.decodeResource(getResources(), R.drawable.basketball);
        mBitmap = Bitmap.createScaledBitmap(ball, BALL_SIZE, BALL_SIZE, true);
        Bitmap basket = BitmapFactory.decodeResource(getResources(),R.drawable.basket);
        mBasket = Bitmap.createScaledBitmap(basket, BASKET_SIZE, BASKET_SIZE, true);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inDither = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        mField = BitmapFactory.decodeResource(getResources(), R.drawable.court ,opts);
        mBall = new Particle();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        mHorizontalBound = 500;
        mVerticalBound = 500;
        mXOrigin = mDisplay.getWidth()/2;
        mYOrigin = mDisplay.getWidth()/2;

    }


    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mField,0,0,null);
        canvas.drawBitmap(mBasket,mXOrigin - BASKET_SIZE/2, mYOrigin - BASKET_SIZE/2, null);

        mBall.updatePosition(mSensorX, mSensorY, mSensorZ, mSensorTimeStamp);
        mBall.resolveCollisionWithBounds(mHorizontalBound, mVerticalBound);
       // canvas.drawBitmap(mBitmap,30,30,null);
        canvas.drawBitmap(mBitmap, (mXOrigin - BALL_SIZE/2) + mBall.mPosX,
               (mYOrigin - BALL_SIZE/2) - mBall.mPosY, null);
        invalidate();
        Log.d("Redrawn:", Float.toString(mSensorX));
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if(mDisplay.getRotation() == Surface.ROTATION_0) {
            mSensorX = event.values[0];
            mSensorY = event.values[1];
            mSensorZ = event.values[2];
            mSensorTimeStamp = event.timestamp;
        }else if(mDisplay.getRotation() == Surface.ROTATION_90) {
            mSensorX = -event.values[1];
            mSensorY = event.values[0];
            mSensorZ = event.values[2];
            mSensorTimeStamp = event.timestamp;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void startSimulation() {
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensors.get(0), sensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stopSimulation() {

        sensorManager.unregisterListener(this);
    }


}
