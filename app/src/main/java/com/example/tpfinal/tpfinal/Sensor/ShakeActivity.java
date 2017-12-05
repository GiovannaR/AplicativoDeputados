package com.example.tpfinal.tpfinal.Sensor;

import android.app.Activity;
import android.hardware.SensorListener;
import android.hardware.SensorManager;

import static android.R.attr.x;

/**
 * Created by giovannariqueti on 04/12/17.
 */

//public class ShakeActivity extends Activity implements SensorListener {

    /*sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);

    @Override
    public void onSensorChanged(int i, float[] floats) {
        if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                x = values[SensorManager.DATA_X];
                y = values[SensorManager.DATA_Y];
                z = values[SensorManager.DATA_Z];

                float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    Log.d("sensor", "shake detected w/ speed: " + speed);
                    Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(int i, int i1) {

    }*/
//}
